package mk.ukim.finki.uiktp.thefoodieexpress.restaurant;

import mk.ukim.finki.uiktp.thefoodieexpress.dish.Dish;
import mk.ukim.finki.uiktp.thefoodieexpress.specifications.Specifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RestaurantRepository
        extends JpaRepository<Restaurant, String>,
        JpaSpecificationExecutor<Restaurant> {
}
