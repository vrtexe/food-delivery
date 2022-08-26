package mk.ukim.finki.uiktp.thefoodieexpress.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart.ShoppingCartItemDetails;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {

    @NotBlank
    private User user;

    @NotBlank
    private String jwt;

    private String shoppingCartId;

    private List<ShoppingCartItemDetails> shoppingCartItems;
}
