package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;

public class ShoppingCartItemNotFound extends NotFoundException {
    public ShoppingCartItemNotFound(String id) {
        super(String.format("The shopping cart item with the provided id [%s] was not found", id));
    }
}
