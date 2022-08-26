package mk.ukim.finki.uiktp.thefoodieexpress.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;
import java.util.function.Function;

public class Specifications {
    
    public static <T, V> Specification<T> equal(SingularAttribute<T, V> attribute, V value) {
        return equal(root -> root.get(attribute), value);
    }
    
    public static <T, V> Specification<T> equal(Function<Root<T>, Expression<V>> fun, V value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(fun.apply(root), value);
    }
    
    public static <T> Specification<T> like(SingularAttribute<T, String> attribute, String value) {
        return like(root -> root.get(attribute), value);
    }
    
    public static <T> Specification<T> like(Function<Root<T>, Expression<String>> fun, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(fun.apply(root)),
                "%" + value.toLowerCase() + "%"
        );
    }
    
    public static <T,V extends Comparable<? super V> > Specification<T> between(
            SingularAttribute<T, V> attribute,
            V fromValue,
            V toValue
    ) {
        return between(root -> root.get(attribute), fromValue, toValue);
    }
    
    public static <T, V extends Comparable<? super V>> Specification<T> between(
            Function<Root<T>, Expression<V>> fun,
             V fromValue,
            V toValue
    ) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(
                        fun.apply(root),
                        fromValue,
                        toValue
                );
    }
    
    public static <T, V extends Comparable<? super V>> Specification<T> greaterThan(SingularAttribute<T, V> attribute, V value) {
        return greaterThan(root -> root.get(attribute), value);
    }
    
    public static <T, V extends Comparable<? super V>> Specification<T> greaterThan(Function<Root<T>, Expression<V>> fun, V value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        fun.apply(root),
                        value
                );
    }
    
    public static <T, V extends Comparable<? super V>> Specification<T> lessThan(SingularAttribute<T, V> attribute, V value) {
        return lessThan(root -> root.get(attribute), value);
    }
    
    public static <T, V extends Comparable<? super V>> Specification<T> lessThan(Function<Root<T>, Expression<V>> fun, V value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        fun.apply(root),
                        value
                );
    }
    
    
    public static <T> Specification<T> join(Collection<Specification<T>> specifications) {
        Specification<T> specification = Specification.where(specifications.stream().findFirst().orElse(null));
        
        return specifications.stream().reduce(specification, (prev, spec) -> prev.and(specification));
    }
}
