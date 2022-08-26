package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import lombok.Data;

@Data
public class ShoppingCartItemDetails {

    private String id;

    private String dishId;

    private Integer quantity;

    public ShoppingCartItemDetails(ShoppingCartItem shoppingCartItem) {
        this.id = shoppingCartItem.getId();
        this.dishId = shoppingCartItem.getDish().getId();
        this.quantity = shoppingCartItem.getQuantity();
    }
}
