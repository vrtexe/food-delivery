package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService {
    
    Page<DishDto> findAll(Pageable pageable);
    
    Page<DishDto> findAllByRestaurant(DishCriteria criteria, Pageable pageable);
    
    DishDto findById(String id);
    
    void create(DishDto dish);
    
    void update(DishDto dish);
    
    void delete(String id);
}
