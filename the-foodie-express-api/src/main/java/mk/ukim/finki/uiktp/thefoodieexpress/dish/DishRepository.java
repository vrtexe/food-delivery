package mk.ukim.finki.uiktp.thefoodieexpress.dish;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, String>, JpaSpecificationExecutor<Dish> {
    @Query(value = """
                select *
                from dish d
                where d.restaurant_id = :restaurant_id
            """, nativeQuery = true)
    Page<Dish> findAllByRestaurantId(@Param("restaurant_id") String restaurantId, Pageable pageable);
}
