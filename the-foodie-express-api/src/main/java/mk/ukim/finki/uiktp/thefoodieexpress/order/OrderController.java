package mk.ukim.finki.uiktp.thefoodieexpress.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;
    
    @GetMapping
    public Page<OrderDto> getAll(@ParameterObject OrderCriteria criteria, @ParameterObject Pageable pageable) {
        
        return orderService.findAllBy(criteria, pageable);
    }
    
    @GetMapping( "/{id}")
    public OrderDto get(@PathVariable(value = "id") String id) {
        return orderService.findById(id);
    }
    
    @PostMapping
    public void create(@RequestBody OrderDto order, Authentication authentication) {
        var email = (String) authentication.getPrincipal();
    
        orderService.create(order, email);
    }
    
    @PutMapping
    public void update(@RequestBody OrderDto order) {
        orderService.update(order);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        orderService.delete(id);
    }
}
