package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.RestaurantNotFound;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {
    
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    
    public Page<DishDto> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable).map(DishDto::fromEntity);
    }
    
    public Page<DishDto> findAllByRestaurant(DishCriteria criteria, Pageable pageable) {
        
        var dishSpecification = DishSpecification.from(criteria);
        
        return dishRepository.findAll(dishSpecification, pageable)
                .map(DishDto::fromEntity);
    }
    
    public DishDto findById(String id) {
        return DishDto.fromEntity(dishRepository.findById(id).orElseThrow(() -> new DishNotFound(id)));
    }
    
    public void create(DishDto dish) {
        var restaurant = restaurantRepository
                .findById(dish.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFound(dish.getRestaurantId()));
        var dishEntity = new Dish();
        
        dishEntity.setName(dish.getName());
        dishEntity.setPrice(dish.getPrice());
        dishEntity.setDescription(dish.getDescription());
        dishEntity.setRestaurant(restaurant);
        dishEntity.setCuisine(dish.getCuisine());
        
        dishRepository.saveAndFlush(dishEntity);
    }
    
    public void update(DishDto dish) {
        var restaurant = restaurantRepository.findById(dish.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFound(dish.getRestaurantId()));
        
        dishRepository.saveAndFlush(DishDto.toEntity(dish, restaurant));
    }
    
    public void delete(String id) throws DishNotFound {
        Dish dish = dishRepository.getById(id);
        dishRepository.delete(dish);
    }
}
