package marcet.repository;

import marcet.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

// List<User> findDistinctUserByAccounts_NameContaining(String str);
//Page<User> users = repository.findAll(PageRequest.of(1, 20));
@Query("select distinct p from Product p left join p.categories categories where categories.title like %?1%")
    List<Product> findDistinctProductsByCategories_TitleContaining(String category);
//    Page<Product> findDistinctProductsByCategories_TitleContaining(Specification<Product> spec, PageRequest.of(int page, int pageSize));

}
