package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;

public class ShoppingCartNotFound extends NotFoundException {
    public ShoppingCartNotFound(String email) {
        super("The shopping cart for the provided user [%s] was not found".formatted(email));
    }
}
