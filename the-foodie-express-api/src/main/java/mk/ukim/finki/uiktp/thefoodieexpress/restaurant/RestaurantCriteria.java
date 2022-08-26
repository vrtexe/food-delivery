package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Cuisine;

import javax.persistence.Id;
import java.util.Collection;

@Data
public class RestaurantCriteria {
    
    private String name;
    
    private Cuisine cuisine;
    
    private Double rating;
    
    private String workingHours;
    
    private String address;
    
    private String phoneNumber;
}
