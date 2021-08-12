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

    public BigDecimal getPriceProduct(int quantity, BigDecimal cost){
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal itemCost;
        itemCost = cost.multiply(new BigDecimal(quantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }


    public ProductDTO saveNewProduct(ProductDTO productDTO) { // LSS добавил метода по сохранению нового продукта
        Product newProduct = new Product();
        productDTO.setId(null);
        newProduct = convertToEntity(productDTO);
        productRepository.save(newProduct);
        productDTO.setId(newProduct.getProductId());
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) { //LSS добавил метод по обновлению продукта
//        Product product = productRepository.findById(productDTO.getId()).get();
        Product product = convertToEntity(productDTO);
        product.setProductId(productDTO.getId());
        productRepository.save(product);
        return convertToDto(product);
    }

    public void deleteProductById(Long product_id) { //LSS добавил удаление продукта из БД
        productRepository.deleteById(product_id);
    }

    public ProductDTO findProductDtoById(Long product_id) { //LSS поиск продукта по id, Optional может лучше отправлять? или здесь isPresent?
        return convertToDto(productRepository.findById(product_id).get());
    }

    public Product findProductById(Long product_id) { //LSS поиск продукта по id, Optional может лучше отправлять? или здесь isPresent?
        return productRepository.findById(product_id).get();
    }

}
