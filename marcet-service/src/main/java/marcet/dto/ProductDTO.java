package marcet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcet.model.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private int cost;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }
}
