package marcet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.ProductDTO;
import marcet.model.*;
import marcet.repository.AddressRepository;
import marcet.repository.OrderItemsRepository;
import marcet.repository.OrderRepository;
import marcet.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @PostConstruct
    public void init() {
        basketList = new ArrayList<>();
    }

    public List<ProductDTO> addProductInBasket(ProductDTO productDTO) {
        int quantity;
        for (int i = 0; i < basketList.size(); i++) {
            if (basketList.get(i).getId() == productDTO.getId()) {
                quantity = basketList.get(i).getQuantity();
                quantity++;
                productDTO.setQuantity(quantity);
                log.info("{}", productDTO.getQuantity());
                basketList.set(i, productDTO);
                log.info("Число продуктов {}  {}  увеличенно на 1 ", productDTO.getId(), productDTO.getTitle());
                return basketList;
            }
        }
        basketList.add(productDTO);
        for (ProductDTO p : basketList) {
            log.info("addProductInBasket Продукты добавленные в корзину {}", p.getTitle());
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


    public Order createOrder(String userName) {
        Product product;
        Order order = new Order();
        LocalDateTime date = LocalDateTime.now();
        OrderItems orderItems = new OrderItems();
        User user = userRepository.findByUsername(userName).get();
        Address address = addressRepository.findByUserId(user.getUserId());

        order.setUserId(user.getUserId());
        order.setTotalQuantity(getTotalQuantity(basketList));
        order.setTotalCost(getTotalCost(basketList));
        order.setAddressId(address.getAddressId());
        order.setCreateTime(date);
        order = orderRepostory.save(order);

        for (ProductDTO p : basketList) {
            log.info("Продукт Id - {}, name - {}, Cost - {}, количество {}, Описание1 - {} описание2 - {}", p.getId(), p.getTitle(), p.getCost(), p.getQuantity(), p.getFullDescription(), p.getShortDescription());
            log.info(" Tota Quantity {},  Total Cost {} ", getTotalQuantity(basketList), getTotalCost(basketList));
            product = productService.convertToEntity(p);
            orderItemsRepository.save(
                    new OrderItems(
                            order.getOrderId(),
                            p.getId(),
                            p.getQuantity(),
                            productService.getPrictProduct
                                    (
                                            p.getQuantity(),
                                            p.getCost()
                                    ),
                            p.getCost()
                    )
            );
        }
        basketList.clear();
        return order;
    }




    public int getTotalQuantity(List<ProductDTO> productDTOList) {
        int totalQuantity = 0;
        for (ProductDTO p : productDTOList) {
            totalQuantity = totalQuantity + p.getQuantity();
        }
        return totalQuantity;
    }

    public BigDecimal getTotalCost(List<ProductDTO> productDTOList) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (ProductDTO p : productDTOList) {
            totalCost = totalCost.add(p.getCost());
        }
        return totalCost;
    }
}