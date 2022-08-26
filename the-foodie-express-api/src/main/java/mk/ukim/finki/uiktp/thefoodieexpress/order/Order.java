package mk.ukim.finki.uiktp.thefoodieexpress.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "app_order")
@Getter
@Setter
public class Order {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private LocalDateTime dateTime;
    
    private Double price;
    
    private String address;
    
    @ManyToOne
    private User user;
}
