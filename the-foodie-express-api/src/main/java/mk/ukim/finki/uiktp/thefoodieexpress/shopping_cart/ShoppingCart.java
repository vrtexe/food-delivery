package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ShoppingCart {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @OneToOne
    private User user;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY)
    private Collection<ShoppingCartItem> items;
    
    
    public ShoppingCart(User user) {
        this.user = user;
    }
}
