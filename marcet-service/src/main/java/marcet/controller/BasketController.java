package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/http://localhost:8701/zuul/")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/add")
    public List<ProductDTO> addProductInBasket(@RequestBody ProductDTO productDTO) {
        return basketService.addProductInBasket(productDTO);
    }

    @GetMapping("/get-basket")
    public List<ProductDTO> getBasket() {
        return basketService.getBasket();
    }

    @PostMapping("/del")
    public List<ProductDTO> delProductOfBasket(@RequestBody ProductDTO productDTO) {
        return basketService.delProductOfBasket(productDTO);
    }


}
