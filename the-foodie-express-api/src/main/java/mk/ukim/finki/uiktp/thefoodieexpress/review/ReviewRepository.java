package mk.ukim.finki.uiktp.thefoodieexpress.review;

import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    boolean existsByRestaurantAndUser(Restaurant restaurant, User user);

    long countAllByRestaurant(Restaurant restaurant);
}
