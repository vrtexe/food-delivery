package mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart;

import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    Optional<ShoppingCart> findByUser(User user);
}
