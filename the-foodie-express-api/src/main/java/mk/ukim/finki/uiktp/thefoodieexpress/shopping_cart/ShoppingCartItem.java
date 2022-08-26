package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish;
import mk.ukim.finki.uiktp.thefoodieexpress.order.Order;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShoppingCartItem {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;
    
    @ManyToOne
    private Dish dish;
    
    private Integer quantity;
    
    @ManyToOne
    private Order order;
}
