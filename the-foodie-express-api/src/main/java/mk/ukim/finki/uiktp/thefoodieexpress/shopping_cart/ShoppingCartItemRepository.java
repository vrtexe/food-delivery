package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import mk.ukim.finki.uiktp.thefoodieexpress.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, String> {
    
    List<ShoppingCartItem> findAllByOrder(Order order);
    
    List<ShoppingCartItem> findAllByShoppingCart(ShoppingCart shoppingCart);
    
    Optional<ShoppingCartItem> findAllByShoppingCartIdAndDishId(String shoppingCartId, String dishId);
}
