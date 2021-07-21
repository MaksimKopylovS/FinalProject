package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/add")
    public List<ProductDTO> addProductInBasket(@RequestBody ProductDTO productDTO) {
        //System.out.println(productDTO.getTitle());
        return basketService.addProductInBasket(productDTO);
    }

    @GetMapping("/get-basket")
    public List<ProductDTO> getBasket() {
        System.out.println("GETBASKET");
        return basketService.getBasket();
    }

    @PostMapping("/del")
    public List<ProductDTO> delProductOfBasket(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.getTitle() + "  delPruductOfBasket");
        return basketService.delProductOfBasket(productDTO);
    }


}
