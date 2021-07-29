package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.model.Product;
import marcet.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

//    public List<ProductDTO> getProduct() {
//        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
//    }

    public Page<ProductDTO> findAllProducts(Specification<Product> spec, int page, int pageSize) {
        Page<ProductDTO> productDTOS = productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDTO::new);
        System.out.println(productDTOS.getTotalElements() + " " + productDTOS.getTotalPages());
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDTO::new);
    }
}
