package mk.ukim.finki.uiktp.thefoodieexpress.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.Restaurant;
import mk.ukim.finki.uiktp.thefoodieexpress.restaurant.RestaurantDto;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class OrderDto {
    
    private String id;
    
    private LocalDateTime dateTime;
    
    private double price;
    
    private String address;
    
    public static OrderDto fromEntity(Order entity) {
        var restaurant = new OrderDto.OrderDtoBuilder();
        
        restaurant.id(entity.getId())
                .address(entity.getAddress())
                .dateTime(entity.getDateTime())
                .price(entity.getPrice());
        
        return restaurant.build();
    }
    
    public static Order toEntity(OrderDto dto, User user) {
        var order = new Order();
        
        order.setId(dto.getId());
        order.setAddress(dto.getAddress());
        order.setDateTime(dto.getDateTime());
        order.setPrice(dto.getPrice());
        order.setUser(user);
        
        return order;
    }
}
