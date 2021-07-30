package marcet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcet.model.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal cost;

    public ProductDTO(Product product) {
        this.id = product.getProductId();
        this.title = product.getTitle();
        this.cost = product.getPrice();
    }
}
