package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.DishNotFound;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.DishRepository;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.InvalidInput;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import mk.ukim.finki.uiktp.thefoodieexpress.user.UserNotFound;
import mk.ukim.finki.uiktp.thefoodieexpress.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    
    private final ShoppingCartRepository shoppingCartRepository;
    private final Validator validator;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    
    @Override
    @Transactional
    public ShoppingCartItem addItem(ShoppingCartItemDto shoppingCartItemDto, Authentication authentication) throws InvalidInput, UserNotFound, ShoppingCartNotFound, DishNotFound {
        this.validateItemData(shoppingCartItemDto);
        
        String email = (String) authentication.getPrincipal();
        ShoppingCart shoppingCart = this.findShoppingCartByUser(email);
        
        String id = shoppingCartItemDto.getDishId();
        Dish dish = this.findDishById(id);
        
        Integer quantity = shoppingCartItemDto.getQuantity();
        
        var item = shoppingCartItemRepository.findAllByShoppingCartIdAndDishId(shoppingCart.getId(), dish.getId())
                .orElse(null);
        
        item =
                item == null
                        ? new ShoppingCartItem(null, shoppingCart, dish, quantity, null)
                        : new ShoppingCartItem(
                        item.getId(),
                        item.getShoppingCart(),
                        item.getDish(),
                        item.getQuantity() + quantity,
                        null
                );
        return shoppingCartItemRepository.saveAndFlush(item);
    }
    
    @Override
    @Transactional
    public void removeItem(String id) throws ShoppingCartItemNotFound {
        var item = findItemById(id);
        
        shoppingCartItemRepository.delete(item);
    }
    
    private void validateItemData(ShoppingCartItemDto shoppingCartItemDto) {
        List<ConstraintViolation<ShoppingCartItemDto>> constraintViolations = new ArrayList<>(this.validator.validate(shoppingCartItemDto));
        
        if (constraintViolations.size() > 0) {
            throw new InvalidInput(constraintViolations.get(0).getMessage());
        }
    }
    
    private ShoppingCart findShoppingCartByUser(String email) throws UserNotFound {
        User user = this.findUserById(email);
        
        return this.shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new ShoppingCartNotFound(email));
    }
    
    private User findUserById(String email) {
        return this.userRepository.findById(email)
                .orElseThrow(() -> new UserNotFound(email));
    }
    
    private Dish findDishById(String id) {
        return this.dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFound(id));
    }
    
    private ShoppingCartItem findItemById(String id) {
        return this.shoppingCartItemRepository.findById(id)
                .orElseThrow(() -> new ShoppingCartItemNotFound(id));
    }
}
