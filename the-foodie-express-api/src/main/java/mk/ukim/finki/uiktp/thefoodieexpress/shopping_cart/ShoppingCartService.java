package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import org.springframework.security.core.Authentication;

public interface ShoppingCartService {

    ShoppingCartItem addItem(ShoppingCartItemDto shoppingCartItemDto, Authentication authentication);

    void removeItem(String id);
}
