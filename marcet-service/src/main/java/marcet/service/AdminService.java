package marcet.service;

import lombok.AllArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.model.Product;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {

    private final ProductService productService;

    public void saveBD(ProductDTO productDTO){
        Product p = productService.convertToEntity(productDTO);
    }
}
