package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.service.ProductService;
import marcet.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String products() {
        System.out.println("MarcetServiceControlelr Запрос пршол");

        return "Держи Пакет продуктов";
    }


    @GetMapping("/get-products")
    public List<ProductDTO> getProducts() {
        System.out.println("Запрос на продукты пришол");
        return productService.getProduct();
    }


    @RequestMapping(value = "/get-products-all", method = RequestMethod.GET)
    public Page<ProductDTO> findAllProducts(@RequestParam MultiValueMap<String, String> params,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        for (int i = 0; i < params.size(); i++) {
            System.out.println(params.toSingleValueMap());
        }

        return productService.findAllProducts(ProductSpecifications.build(params), page, 5);
    }

    @PostMapping("/add-basket")
    public void addBasketProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.getTitle());
        // return new ArrayList<>();
    }


}
