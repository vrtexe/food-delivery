package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Cuisine;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;

@Data
@Builder
@AllArgsConstructor
public class DishDto {
    
    private String id;
    
    private String name;
    
    private Double price;
    
    private String description;
    
    private String restaurantId;
    
    private Cuisine cuisine;
    
    public static DishDto fromEntity(Dish entity) {
        var dish = new DishDto.DishDtoBuilder();
        
        dish.id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .restaurantId(entity.getRestaurant().getId())
                .cuisine(entity.getCuisine());
        
        return dish.build();
    }
    
    public static Dish toEntity(DishDto dto, Restaurant restaurant) {
        var dish = new Dish();
        
        dish.setId(dto.getId());
        dish.setName(dto.getName());
        dish.setPrice(dto.getPrice());
        dish.setDescription(dto.getDescription());
        dish.setCuisine(dto.getCuisine());
        dish.setRestaurant(restaurant);
        
        return dish;
    }
}
