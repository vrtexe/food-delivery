package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Cuisine;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishCriteria {
    private Cuisine cuisine;
    private String restaurantId;
}
