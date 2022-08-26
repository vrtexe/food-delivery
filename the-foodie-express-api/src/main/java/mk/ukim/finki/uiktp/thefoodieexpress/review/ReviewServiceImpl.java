package mk.ukim.finki.uiktp.thefoodieexpress.review;

import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.order.Order;
import mk.ukim.finki.uiktp.thefoodieexpress.order.OrderRepository;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.RestaurantNotFound;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.RestaurantRepository;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.InvalidInput;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.BadRequestException;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;
import mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart.ShoppingCartItem;
import mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart.ShoppingCartItemRepository;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import mk.ukim.finki.uiktp.thefoodieexpress.user.UserNotFound;
import mk.ukim.finki.uiktp.thefoodieexpress.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final Validator validator;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    
    @Override
    public Review submit(ReviewDto reviewDto, Authentication authentication) throws InvalidInput, RestaurantNotFound, UserNotFound {
        this.validateReviewData(reviewDto);
        
        String id = reviewDto.getRestaurantId();
        Restaurant restaurant = this.findRestaurantById(id);
        
        String email = (String) authentication.getPrincipal();
        User user = this.findUserByEmail(email);
        
        boolean hasSubmitted = this.reviewRepository.existsByRestaurantAndUser(restaurant, user);
        
        if (hasSubmitted) {
            throw new ReviewAlreadyExists(restaurant.getName());
        }
        
        List<Order> orders = this.findAllOrdersByRestaurantAndUser(restaurant, user);
        boolean hasOrdered = (orders != null && !orders.isEmpty());
        
        if (!hasOrdered) {
            String message = "An order item from the provided restaurant [%s] was not found".formatted(restaurant.getName());
            throw new BadRequestException(message);
        }
        
        Integer score = reviewDto.getScore();
        
        Review review = this.reviewRepository.save(new Review(restaurant, score, user));
        
        int count = (int) this.reviewRepository.countAllByRestaurant(restaurant);
        Double oldAverage = restaurant.getRating();
        Double newAverage = oldAverage + ((score - oldAverage) / (count + 1));
        
        restaurant.setRating(newAverage);
        this.restaurantRepository.save(restaurant);
        
        return review;
    }
    
    private void validateReviewData(ReviewDto reviewDto) {
        List<ConstraintViolation<ReviewDto>> constraintViolations = new ArrayList<>(this.validator.validate(reviewDto));
        
        if (constraintViolations.size() > 0) {
            throw new InvalidInput(constraintViolations.get(0).getMessage());
        }
    }
    
    private Restaurant findRestaurantById(String id) {
        return this.restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFound(id));
    }
    
    private User findUserByEmail(String email) {
        return this.userRepository.findById(email)
                .orElseThrow(() -> new UserNotFound(email));
    }
    
    private List<Order> findAllOrdersByRestaurantAndUser(Restaurant restaurant, User user) {
        List<Order> orders = this.orderRepository.findAllByUser(user);
        
        Predicate<Order> byRestaurant = order -> {
            List<ShoppingCartItem> items = this.shoppingCartItemRepository.findAllByOrder(order);
            
            int count = (int) items.stream()
                    .filter(item -> item.getDish().getRestaurant().equals(restaurant))
                    .count();
            
            return count >= 1;
        };
        
        return orders.stream()
                .filter(byRestaurant)
                .collect(Collectors.toList());
    }
}
