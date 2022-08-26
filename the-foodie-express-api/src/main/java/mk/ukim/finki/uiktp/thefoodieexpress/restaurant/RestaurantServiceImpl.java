package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish_;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;
import mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import static mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications.equal;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    
    private final RestaurantRepository restaurantRepository;
    
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
    
    public Page<RestaurantDto> all(RestaurantCriteria criteria, Pageable pageable) {
        
        var restaurantSpecification = RestaurantSpecification.from(criteria);
        
        return restaurantRepository.findAll(restaurantSpecification, pageable)
                .map(RestaurantDto::fromEntity);
    }
    
    public Restaurant getById(String id) {
        return restaurantRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    
    public Restaurant create(RestaurantDto restaurantDto) {
        var entity = RestaurantDto.toEntity(restaurantDto);
        
        return restaurantRepository.saveAndFlush(entity);
    }
    
//    select count(restaurant0_.id) as col_0_0_
//    from restaurant restaurant0_
//    inner join restaurant_dishes dishes1_ on restaurant0_.id=dishes1_.restaurant_id
//    inner join dish dish2_ on dishes1_.dishes_id=dish2_.id
//    inner join restaurant_dishes dishes3_ on restaurant0_.id=dishes3_.restaurant_id
//    inner join dish dish4_ on dishes3_.dishes_id=dish4_.id
//    where dish2_.cuisine=? and dish4_.cuisine=?
    
    
    public Restaurant update(RestaurantDto restaurantDto) {
        var restaurant = getById(restaurantDto.getId());
        
        restaurant.setName(restaurantDto.getName());
        restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant.setRating(restaurantDto.getRating());
        restaurant.setWorkingHours(restaurantDto.getWorkingHours());
        restaurant.setAddress(restaurantDto.getAddress());
        
        return restaurantRepository.saveAndFlush(restaurant);
    }
    
    
    public Boolean delete(String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(NotFoundException::new);
        restaurantRepository.delete(restaurant);
        return true;
    }
}
