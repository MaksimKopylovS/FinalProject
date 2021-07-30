package marcet.repository;

import marcet.model.ProductsCategoriy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriyRepository extends JpaRepository<ProductsCategoriy, Long> {

}
