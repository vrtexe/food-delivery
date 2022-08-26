package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Cuisine;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Dish {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String name;
    
    private Double price;
    
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    
    @Column(length = 510)
    private String description;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;
}
