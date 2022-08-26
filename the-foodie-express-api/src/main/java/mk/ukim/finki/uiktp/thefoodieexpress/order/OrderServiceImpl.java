package mk.ukim.finki.uiktp.thefoodieexpress.order;

import lombok.AllArgsConstructor;
import mk.ukim.finki.uiktp.thefoodieexpress.shared.exceptions.NotFoundException;
import mk.ukim.finki.uiktp.thefoodieexpress.shopping_cart.*;
import mk.ukim.finki.uiktp.thefoodieexpress.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
    
    public Page<OrderDto> findAllBy(OrderCriteria criteria, Pageable pageable) {
        
        var orderSpecification = OrderSpecification.from(criteria);
        
        return orderRepository.findAll(orderSpecification, pageable).map(OrderDto::fromEntity);
    }
    
    public OrderDto findById(String id) {
        return OrderDto.fromEntity(orderRepository.findById(id).orElseThrow(NotFoundException::new));
    }
    
    public void create(OrderDto orderDto, String email) {
        
        var user = userRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        var shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseThrow(() -> new ShoppingCartNotFound(email));
        var shoppingCartItems = shoppingCart.getItems();
        
        var order = OrderDto.toEntity(orderDto, user);
        var orderMenuItems = shoppingCartItems.stream()
                .map(item -> new ShoppingCartItem(item.getId(), null, item.getDish(), item.getQuantity(), order))
                .toList();
        
        orderRepository.saveAndFlush(order);
        shoppingCartItemRepository.saveAllAndFlush(orderMenuItems);
    }
    
    public void update(OrderDto orderDto) {
        var order = orderRepository.findById(orderDto.getId())
                .orElseThrow(NotFoundException::new);
        
        order.setAddress(orderDto.getAddress());
        
        orderRepository.saveAndFlush(order);
    }
    
    public void delete(String id) {
        Order order = orderRepository.getById(id);
        orderRepository.delete(order);
    }
}
