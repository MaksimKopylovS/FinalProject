package marcet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcet.model.Product;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal cost;
    private int quantity = 1;
    private String shortDescription;
    private String fullDescription;
    private String phoroUrl;

    public ProductDTO(Product product) {
        this.id = product.getProductId();
        this.title = product.getTitle();
        this.cost = product.getPrice();
        this.shortDescription = product.getShortDescription();
        this.fullDescription = product.getFullDescription();
        this.phoroUrl = product.getPhoroUrl();
    }
}
