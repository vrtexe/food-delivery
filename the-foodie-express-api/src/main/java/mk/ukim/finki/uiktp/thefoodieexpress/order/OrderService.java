package mk.ukim.finki.uiktp.thefoodieexpress.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface OrderService {
    Page<OrderDto> findAllBy(OrderCriteria criteria, Pageable pageable);
    OrderDto findById(String id);
    void create(OrderDto order, String email);
    void update(OrderDto order);
    void delete(String id);
}
