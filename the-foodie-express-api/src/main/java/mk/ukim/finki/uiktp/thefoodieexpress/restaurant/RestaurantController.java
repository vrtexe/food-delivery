package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    
    private final RestaurantService restaurantService;
    
    @GetMapping
    @PageableAsQueryParam
    public ResponseEntity<Page<RestaurantDto>> getAllRestaurants(@ParameterObject RestaurantCriteria criteria, @ParameterObject Pageable pageable) {
        
        Page<RestaurantDto> result = restaurantService.all(criteria, pageable);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }
    
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto restaurant) {
        return ResponseEntity.ok(restaurantService.create(restaurant));
    }
    
    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantDto restaurant) {
        return ResponseEntity.ok(restaurantService.update(restaurant));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable(value = "id") String id) {
        if (restaurantService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
