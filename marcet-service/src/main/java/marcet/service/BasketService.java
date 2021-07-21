package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketService {

    private ArrayList<ProductDTO> basketList;

    @PostConstruct
    public void init() {
        basketList = new ArrayList<>();
    }

    public List<ProductDTO> addProductInBasket(ProductDTO productDTO) {
        basketList.add(productDTO);
        for (ProductDTO p : basketList) {
            System.out.println(p.getTitle());
        }
        return basketList;
    }


    public List<ProductDTO> getBasket() {
        return basketList;
    }

    public List<ProductDTO> delProductOfBasket(ProductDTO productDTO) {
        ProductDTO p = basketList.stream().filter(pr -> pr.getTitle().equals(productDTO.getTitle())).findFirst().get();
        basketList.remove(p);
        return basketList;
    }
}
