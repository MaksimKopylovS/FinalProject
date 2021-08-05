package marcet.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "order_items_tbl")
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity_fld")
    private int quantity;

    @Column(name = "price_fld")
    private BigDecimal price;

    @Column(name = "cost_fld")
    private BigDecimal cost;

    public OrderItems (Long orderId, Long productId, int quantity, BigDecimal price, BigDecimal cost){
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.cost = cost;
    }

}
