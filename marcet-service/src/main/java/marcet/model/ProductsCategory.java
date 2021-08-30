package marcet.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "products_categories_tbl")
public class ProductsCategory {

    @Id
    @Column(name = "id")
    private Long productId;

    @Column(name = "category_id")
    private Long categoryId;
}
