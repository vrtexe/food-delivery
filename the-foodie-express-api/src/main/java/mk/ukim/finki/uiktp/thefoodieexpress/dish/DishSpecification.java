package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Cuisine;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant_;
import mk.ukim.finki.uiktp.thefoodieexpress.specifications.AndSpecification;
import org.springframework.data.jpa.domain.Specification;

import static mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications.*;

public class DishSpecification {
    private DishSpecification() {
    }
    
    public static Specification<Dish> from(DishCriteria criteria) {
        
        var spec = new AndSpecification<Dish>();
        
        if (criteria.getRestaurantId() != null) {
            spec.add(restaurantIdSpecification(criteria.getRestaurantId()));
        }
        
        if (criteria.getCuisine() != null) {
            spec.add(cuisineSpecification(criteria.getCuisine()));
        }
        
        return spec.join();
    }
    
    private static Specification<Dish> restaurantIdSpecification(String restaurantID) {
        return equal((root) -> root.join(Dish_.restaurant).get(Restaurant_.id), restaurantID);
    }
    
    private static Specification<Dish> cuisineSpecification(Cuisine cuisine) {
        return equal(Dish_.cuisine, cuisine);
    }
    
}
