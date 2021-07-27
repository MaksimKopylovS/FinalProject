package marcet.repository;

import marcet.model.Categoriy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriyRepository extends JpaRepository<Categoriy, Long> {

}
