package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAll();
    Page<RestaurantDto> all(RestaurantCriteria criteria, Pageable pageable);
    Restaurant getById(String id);
    Restaurant create(RestaurantDto restaurant);
    Restaurant update(RestaurantDto restaurant);
    Boolean delete(String id);
}
