package mk.ukim.finki.uiktp.thefoodieexpress.order;

import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByUser(User user);
    Page<Order> findAll(Specification<Order> specification, Pageable pageable);
}
