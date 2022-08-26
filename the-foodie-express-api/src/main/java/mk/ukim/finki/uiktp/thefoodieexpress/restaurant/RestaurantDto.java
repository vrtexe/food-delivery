package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
public class RestaurantDto {
    private String id;
    
    private String name;
    
    private double rating;
    
    private String workingHours;
    
    private String address;
    
    private String phoneNumber;
    
    public static RestaurantDto fromEntity(Restaurant entity) {
        var restaurant = new RestaurantDtoBuilder();
        
        restaurant
                .id(entity.getId())
                .address(entity.getAddress())
                .rating(entity.getRating())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .workingHours(entity.getWorkingHours());
        
        return restaurant.build();
    }
    
    public static Restaurant toEntity(RestaurantDto dto) {
        var restaurant = new Restaurant();
        
        restaurant.setName(dto.getName());
        restaurant.setPhoneNumber(dto.getPhoneNumber());
        restaurant.setRating(dto.getRating());
        restaurant.setWorkingHours(dto.getWorkingHours());
        restaurant.setAddress(dto.getAddress());
        
        return restaurant;
    }
    
}
