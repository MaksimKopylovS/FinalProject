package marcet.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "products_tbl")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

//    @Column(name = "categoriy_id")
//    private Long categoriyId;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "price_fld")
    private BigDecimal price;

    @Column(name = "short_description_fld")
    private String shortDescription;

    @Column(name = "full_description_fld")
    private String fullDescription;

    @Column(name = "photo_url_fld")
    private String phoroUrl;

    @ManyToMany // LSS добавил связь с таблицей катеогрий и добавил новый парметр коллекцию категорий,
    @JoinTable(name = "products_categories_tbl",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories; // ?? или здесь лучше List<Category>?

//    @OneToMany
//    @JoinTable(name = "order_items_tbl",
//            joinColumns = @JoinColumn(name = "product_id"))
//    private Collection<Product> orderCollection;
//
//    @OneToMany
//    @JoinTable(name = "products_categories_tbl",
//            joinColumns = @JoinColumn(name = "product_id"))
//    private Collection<Product> productsCategoriyCollection ;

//    @OneToMany
//    @JoinTable(name = "order_items_tbl",
//            joinColumns = @JoinColumn(name = "product_id"))
//    private Collection<Product> orderCollection;
//
//    @ManyToMany
//    @JoinTable(name = "products_categories_tbl",
//            joinColumns = @JoinColumn(name = "categories_id"))
//    private Collection<Category> productsCategoriyCollection ;

}
