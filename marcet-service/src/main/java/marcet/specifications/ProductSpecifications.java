package marcet.specifications;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import marcet.model.Category;
import marcet.model.Product;
import marcet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;


public class ProductSpecifications {



    private static Specification<Product> idFilter(Long lon) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("productId"), lon);
    }

    private static Specification<Product> idCategoryFilter(String category) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("categories"), category);
    }

    private static Specification<Product> priceGreaterOrEqualsThan(BigDecimal minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Product> priceLesserOrEqualsThan(BigDecimal maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Product> titleLike(String titlePart) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%%s%%", titlePart));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {

        Specification<Product> spec = Specification.where(null);

        if (params.containsKey("id") && !params.getFirst("id").isBlank()) {
            System.out.println("Приход B  params.containsKey(id)");
            spec = spec.and(ProductSpecifications.idFilter(Long.parseLong(params.getFirst("id"))));
        }

        if (params.containsKey("category") && !params.getFirst("category").isBlank()) {
            spec = spec.and(ProductSpecifications.idCategoryFilter(params.getFirst("category")));
        }

        if (params.containsKey("min_cost") && !params.getFirst("min_cost").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(BigDecimal.valueOf(Long.parseLong(params.getFirst("min_cost")))));
        }
        if (params.containsKey("max_cost") && !params.getFirst("max_cost").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(BigDecimal.valueOf(Long.parseLong(params.getFirst("max_cost")))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(params.getFirst("title")));
        }
        return spec;
    }

}
