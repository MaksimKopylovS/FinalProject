package marcet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.BasketItemDTO;
import marcet.dto.ProductDTO;
import marcet.model.*;
import marcet.repository.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Scope(scopeName = "prototype")
public class BasketService {

    private static ArrayList<ProductDTO> basketList;
    private final ProductService productService;
    private final OrderRepository orderRepostory;
    private final OrderItemsRepository orderItemsRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;
    private final BasketItemRepository basketItemRepository;

    @PostConstruct
    public void init() {
        basketList = new ArrayList<>();
    } // LSS это теперь не надо наверное

    public List<BasketItemDTO> addProductToBasket(ProductDTO productDTO, String username) { //LSS добавление товара в корзину и увеличение кол-ва
        User user = userRepository.findByUsername(username).get();
        Product product = productService.findProductById(productDTO.getId());
        List<BasketItemDTO> basketlist = getBasket(username);
        for (int i = 0; i < basketlist.size(); i++) {
            if (basketlist.get(i).getProductDTO().getId() == productDTO.getId()) {
                BasketItem basketItem = basketItemRepository.findById(basketlist.get(i).getBasketItemId()).get();
                basketItem.incQuantity();
                basketItemRepository.save(basketItem);
                return getBasket(username);
            }
        }
        BasketItem newBasketItem = new BasketItem(user, product);
        basketItemRepository.save(newBasketItem);
        return getBasket(username);
    }


    public List<BasketItemDTO> getBasket(String username) { //LSS показать всю корзину
        List<BasketItemDTO> basketlist = new ArrayList<>();
        User user = userRepository.findByUsername(username).get();
        basketlist = basketItemRepository.findAllByUser(user).stream().map(BasketItemDTO::new).collect(Collectors.toList());
        return basketlist;
    }


    public List<BasketItemDTO> delProductFromBasket(ProductDTO productDTO, String username) { //LSS удалить товар из корзины
        User user = userRepository.findByUsername(username).get();
        List<BasketItemDTO> basketlist = getBasket(username);
        BasketItemDTO basketItemDTO = basketlist.stream().filter(bi -> bi.getProductDTO().equals(productDTO)).findFirst().get();
        basketItemRepository.deleteById(basketItemDTO.getBasketItemId());
        log.info("Продукт {} удалён удалён из корзины",productDTO.getTitle());
        return getBasket(username);
    }


    public int getTotalQuantity(String username){ //LSS получить общее кол-во в корзине
        int totalQuantity = 0;
        List<BasketItemDTO> basketlist = getBasket(username);
        for (BasketItemDTO bi: basketlist) {
            totalQuantity = totalQuantity + bi.getQuantity();
        }
        return totalQuantity;
    }

    public BigDecimal getTotalCost(String username) { //LSS получить общую стоимость корзины
        BigDecimal totalCost = BigDecimal.ZERO;
        List<BasketItemDTO> basketlist = getBasket(username);
        for (BasketItemDTO bi: basketlist) {
            totalCost = totalCost.add(calculateCost(bi.getQuantity(), bi.getProductDTO().getPrice()));
        }
        return totalCost;
    }

    public BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice){
        BigDecimal itemCost = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;

        itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }

    public List<BasketItemDTO> decrementProduct(ProductDTO productDTO, String username) { //LSS уменьшить кол-во товара в корзине
        //User user = userRepository.findByUsername(username).get();
        List<BasketItemDTO> basketlist = getBasket(username);
        BasketItemDTO basketItemDTO = basketlist.stream().filter(bi -> bi.getProductDTO().equals(productDTO)).findFirst().get();
        BasketItem basketItem = basketItemRepository.findById(basketItemDTO.getBasketItemId()).get();
        basketItem.decQuantity();
        basketItemRepository.save(basketItem);
        log.info("Продукт {} уменьшено кол-во из корзины",productDTO.getTitle());
        return getBasket(username);
    }

    public void clearBasket(String username) { //LSS очистка корзины
        User user = userRepository.findByUsername(username).get();
        List<BasketItem> basketItems = basketItemRepository.findAllByUser(user);
        for (BasketItem bi: basketItems) {
            basketItemRepository.delete(bi);
        }
    }
}