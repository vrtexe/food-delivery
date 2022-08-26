package mk.ukim.finki.uiktp.thefoodieexpress.review;

import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
public class Review {

    @Id
    private String id;

    @ManyToOne
    private Restaurant restaurant;

    private Integer score;

    @ManyToOne
    private User user;

    public Review() {
        this.id = UUID.randomUUID().toString();
    }

    public Review(Restaurant restaurant, Integer score, User user) {
        this.id = UUID.randomUUID().toString();
        this.restaurant = restaurant;
        this.score = score;
        this.user = user;
    }
}
