package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.CategoryDTO;
import marcet.dto.ProductDTO;
import marcet.model.Category;
import marcet.model.Product;
import marcet.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDTO> getProduct() {
        List<Product> productList = productRepository.findAll();
        Type listType = new TypeToken<List<ProductDTO>>(){}.getType();
        List<ProductDTO> productDTOList = modelMapper.map(productList,listType);
        return productDTOList;


       // return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public Page<ProductDTO> findAllProducts(Specification<Product> spec, int page, int pageSize) {
        Page<ProductDTO> productDTOS = productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDTO::new);
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDTO::new);
    }

    public ProductDTO convertToDto(Product product){
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }
    public Product convertToEntity(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        return product;
    }

    public BigDecimal getPrictProduct(int quantity, BigDecimal cost){
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal itemCost;
        itemCost = cost.multiply(new BigDecimal(quantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }
}
