package marcet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    public Order(Long idUser, Long idProduct, Long orderNumber, BigDecimal sumCost, String adres){
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.orderNumber = orderNumber;
        this.sumCost = sumCost;
        this.adres = adres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "sum_cost")
    private BigDecimal sumCost;

    @Column(name = "adres")
    private String adres;

   // private int quantity;

    //private Long adress_id;

}
