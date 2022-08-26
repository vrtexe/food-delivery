package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
public class Restaurant {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String name;
    
    private Double rating;
    
    @Column(length = 510)
    private String workingHours;
    
    private String address;
    
    private String phoneNumber;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private Collection<Dish> dishes;
}
