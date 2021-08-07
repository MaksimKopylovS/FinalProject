package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.model.Product;
import marcet.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

//    public List<ProductDTO> getProduct() {
//        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
//    }

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

    public ProductDTO findProductById(Long product_id) { //LSS поиск продукта по id, Optional может лучше отправлять? или здесь isPresent?
        return convertToDto(productRepository.findById(product_id).get());
    }
}
