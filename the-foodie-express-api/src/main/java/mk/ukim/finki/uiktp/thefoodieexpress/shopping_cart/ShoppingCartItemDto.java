package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ShoppingCartItemDto {

    @NotBlank(message = "The dish's id cannot be null or whitespace")
    @Size(min = 1, max = 255, message = "The dish's id must be between 1 and 255 characters")
    private String dishId;

    @NotNull(message = "The quantity cannot be null")
    @Min(value = 1, message = "The quantity should not be less than 1")
    private Integer quantity;
}
