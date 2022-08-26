package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {
    
    private final ShoppingCartService shoppingCartService;
    
    @PostMapping("/add-item")
    public ResponseEntity<ShoppingCartItem> createShoppingCartItem(@RequestBody ShoppingCartItemDto shoppingCartItemDto, Authentication authentication) {
        return ResponseEntity.ok(this.shoppingCartService.addItem(shoppingCartItemDto, authentication));
    }
    
    @DeleteMapping("/remove-item/{id}")
    public void deleteShoppingCartItem(@PathVariable String id) {
        this.shoppingCartService.removeItem(id);
    }
}
