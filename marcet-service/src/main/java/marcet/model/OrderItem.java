package marcet.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_items_id")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne // LSS добавил связь и изменил тип данных
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne // LSS добавил связь и изменил тип данных
    @Column(name = "product_id")
    private Product product;

    @Column(name = "quantity_fld")
    private int quantity;

    @Column(name = "price_fld")
    private BigDecimal price;

    @Column(name = "cost_fld")
    private BigDecimal cost;

}
