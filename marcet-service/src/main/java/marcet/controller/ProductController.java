package marcet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.ProductDTO;
import marcet.service.ProductService;
import marcet.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

//    @GetMapping("/products")
//    public String products() {
//        System.out.println("MarcetServiceControlelr Запрос пршол");
//
//        return "Держи Пакет продуктов";
//    }


    @GetMapping("/get-products")
    public List<ProductDTO> getProducts() {
        return productService.getProduct();
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createNewProducts(@RequestBody ProductDTO productDTO){
        return new ProductDTO();
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO editProducts(@RequestBody ProductDTO productDTO){
        return new ProductDTO();
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public Page<ProductDTO> findAllProducts(@RequestParam MultiValueMap<String, String> params,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        for (int i = 0; i < params.size(); i++) {
            log.info("Продукты добавленные в корзину {}", params.toSingleValueMap());
        }

        return productService.findAllProducts(ProductSpecifications.build(params), page, 5);
    }

    @RequestMapping(value = "/get-all-admin", method = RequestMethod.GET)
    public Page<ProductDTO> findAllProductsAdm(@RequestParam MultiValueMap<String, String> params,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        for (int i = 0; i < params.size(); i++) {
            log.info("Продукты добавленные в корзину {}", params.toSingleValueMap());
        }

        return productService.findAllProducts(ProductSpecifications.build(params), page, 5);
    }

    @PostMapping("/add-basket")
    public void addBasketProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.getTitle());
        // return new ArrayList<>();
    }


}
