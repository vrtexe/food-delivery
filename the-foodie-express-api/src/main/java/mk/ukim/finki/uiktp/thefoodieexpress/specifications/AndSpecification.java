package mk.ukim.finki.uiktp.thefoodieexpress.specifications;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;

public class AndSpecification<T> {
    Collection<Specification<T>> specifications = new ArrayList<>();
    
    public AndSpecification<T> add(Specification<T> specification) {
        specifications.add(specification);
        return this;
    }
    
    public Specification<T> join() {
        return Specifications.join(specifications);
    }
}
