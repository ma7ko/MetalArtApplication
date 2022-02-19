package mk.ukim.finki.metalartapplication.model.dto.search;

import mk.ukim.finki.metalartapplication.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONObject;

public class ProductSpecification implements Specification<Product> {

    private final JSONObject criteria;
    private List<Predicate> filters;

    public ProductSpecification(JSONObject criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Iterator<?> keys = criteria.keySet().iterator();
        List<Predicate> filters = new ArrayList<>();

        if (criteria.size() != 0) {

            while (keys.hasNext()) {
                String key = (String) keys.next();
                String filterValue = null;

                try {
                    filterValue = criteria.get(key).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (filterValue != null) {
                    filters.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get(key)), "%" + filterValue.toUpperCase() + "%"));
                }
            }
        }
        //this is the point : didn't know we could concatenate multiple predicates into one.
        return criteriaBuilder.and(filters.toArray(new Predicate[filters.size()]));
    }
}
