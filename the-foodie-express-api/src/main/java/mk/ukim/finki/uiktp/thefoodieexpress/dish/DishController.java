package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dishes")
public class DishController {
    
    private final DishService dishService;
    
    @GetMapping
    public Page<DishDto> getAllDishesByRestaurant(@ParameterObject DishCriteria criteria, @ParameterObject Pageable pageable) {
        return dishService.findAllByRestaurant(criteria, pageable);
    }
    
    @GetMapping("/{id}")
    public DishDto getDishById(@PathVariable(value = "id") String id) {
        return dishService.findById(id);
    }
    
    @PostMapping
    public void createDish(@RequestBody DishDto dish) {
        dishService.create(dish);
    }
    
    @PutMapping
    public void updateDish(@RequestBody DishDto dish) {
        dishService.update(dish);
    }
    
    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable(value = "id") String id) {
        dishService.delete(id);
    }
}
