package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish_;
import mk.ukim.finki.uiktp.thefoodieexpress.specifications.AndSpecification;
import mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications;
import org.springframework.data.jpa.domain.Specification;


import static mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications.*;


public class RestaurantSpecification {
    
    private RestaurantSpecification() {
    }
    
    public static Specification<Restaurant> from(RestaurantCriteria criteria) {
        
        var spec = new AndSpecification<Restaurant>();
        
        if (criteria.getName() != null) {
            spec.add(nameSpecification(criteria.getName()));
        }
        
        if (criteria.getAddress() != null && !criteria.getAddress().isBlank()) {
            spec.add(addressSpecification(criteria.getAddress()));
        }
        
        if (criteria.getRating() != null) {
            spec.add(ratingSpecification(criteria.getRating()));
        }
        
        if (criteria.getWorkingHours() != null && !criteria.getWorkingHours().isBlank()) {
            spec.add(workingHoursSpecification(criteria.getWorkingHours()));
        }
        
        if (criteria.getPhoneNumber() != null && !criteria.getPhoneNumber().isBlank()) {
            spec.add(phoneSpecification(criteria.getPhoneNumber()));
        }
        
        if (criteria.getCuisine() != null) {
            spec.add(cuisineSpecification(criteria.getCuisine()));
        }
        
        return spec.join();
    }
    
    private static Specification<Restaurant> nameSpecification(String name) {
        return like(Restaurant_.name, "%" + name + "%");
    }
    
    private static Specification<Restaurant> addressSpecification(String address) {
        return like(Restaurant_.address, address);
    }
    
    private static Specification<Restaurant> workingHoursSpecification(String workingHours) {
        return like(Restaurant_.workingHours, workingHours);
    }
    
    private static Specification<Restaurant> ratingSpecification(Double rating) {
        return equal(Restaurant_.rating, rating);
    }
    
    private static Specification<Restaurant> cuisineSpecification(Cuisine cuisine) {
        return equal((root) -> root.join(Restaurant_.dishes).get(Dish_.cuisine), cuisine);
    }
    
    private static Specification<Restaurant> phoneSpecification(String number) {
        return like(Restaurant_.phoneNumber, number + "%");
    }
}
