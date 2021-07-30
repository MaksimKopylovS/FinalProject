package marcet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders_tbl")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_quantity_fld")
    private int totalQuantity;

    @Column(name = "total_cost_fld")
    private BigDecimal totalCost;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "create_at")
    @CreationTimestamp
    private Date updateAt;

//    @ManyToMany
//    @JoinTable(name = "order_items_tbl",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private Collection<OrderItems> ordersItemCollection;
//
//
//    @ManyToMany
//    @JoinTable(name = "categories_tbl",
//            joinColumns = @JoinColumn(name = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "title_id"))
//    private Collection<Categories> addressCollection;
}
