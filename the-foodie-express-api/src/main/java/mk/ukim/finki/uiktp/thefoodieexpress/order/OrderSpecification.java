package mk.ukim.finki.uiktp.thefoodieexpress.order;

import mk.ukim.finki.uiktp.thefoodieexpress.specifications.AndSpecification;
import mk.ukim.finki.uiktp.thefoodieexpress.user.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDateTime;

import static mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications.*;

public class OrderSpecification {
    private OrderSpecification() {
    }
    
    public static Specification<Order> from(OrderCriteria criteria) {
        
        var spec = new AndSpecification<Order>();
        
        if (criteria.getUserEmail() != null && !criteria.getUserEmail().isBlank()) {
            spec.add(userEmailSpecification(criteria.getUserEmail()));
        }
        
        if (criteria.getFromDate() != null || criteria.getToDate() != null) {
            spec.add(dateTimeSpecification(criteria.getFromDate(), criteria.getToDate()));
        }
        
        if (criteria.getFromPrice() != null || criteria.getToPrice() != null) {
            spec.add(priceSpecification(criteria.getFromPrice(), criteria.getToPrice()));
        }
        
        if (criteria.getAddress() != null) {
            spec.add(addressSpecification(criteria.getAddress()));
        }
        
        
        return spec.join();
    }
    
    private static Specification<Order> userEmailSpecification(String email) {
        return equal((root) -> root.join(Order_.user).get(User_.email), email);
    }
    
    private static Specification<Order> addressSpecification(String address) {
        return like(Order_.address, "%" + address + "%");
    }
    
    
    private static Specification<Order> dateTimeSpecification(LocalDateTime from, LocalDateTime to) {
        return isBetweenSpecification(Order_.dateTime, from, to);
    }
    
    private static Specification<Order> priceSpecification(Double from, Double to) {
        return isBetweenSpecification(Order_.price, from, to);
    }
    
    private static <T extends Comparable<? super T>> Specification<Order> isBetweenSpecification(SingularAttribute<Order, T> attribute, T from, T to) {
        if (from != null && to != null) {
            return between(attribute, from, to);
        }
        
        if (from != null) {
            return greaterThan(attribute, from);
        }
        
        if (to != null) {
            return lessThan(attribute, to);
        }
        
        return null;
    }
    
}
