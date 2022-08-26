package mk.ukim.finki.uiktp.thefoodieexpress.order;

import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class OrderCriteria {
    
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    
    private Double fromPrice;
    private Double toPrice;
    
    private String address;
    
    private String userEmail;
}
