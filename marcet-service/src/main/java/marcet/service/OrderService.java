package marcet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.ProductDTO;
import marcet.model.Order;
import marcet.model.User;
import marcet.repository.OrderRepository;
import marcet.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Scope(scopeName = "prototype")
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserRepository userRepository;

//    public void createOrder(List<String> data, Long countOrder){
//        log.info("Пришло");
//        for(ProductDTO p : basketService.getBasket()){
//            log.info("Продукт {} {} {} {} {}", p.getId(), p.getTitle(), p.getCost(), p.getFullDescription() , p.getShortDescription());
//        }


//        Order order = new Order();
//        BigDecimal sumCost = getSumCost(basketService.getBasket());
//        System.out.println(data.get(1));
//        User user = userRepository.findByUsername(data.get(1)).get();
//
//
//        for(ProductDTO p: basketService.getBasket()){
//            order.setIdUser(user.getId());
//            order.setIdProduct(p.getId());
//            order.setOrderNumber(countOrder);
//            order.setSumCost(sumCost);
//            order.setAdres(data.get(0));
//            log.info("IdProduct {}, OrderNumber {}, SumCost {}, getAdres {}", order.getIdProduct(), order.getOrderNumber(), order.getSumCost(), order.getAdres());
            //orderRepository.save(order);
//        }
//   }


    public BigDecimal getSumCost(List<ProductDTO> list){
        BigDecimal sumCost = new BigDecimal(0);
        for(ProductDTO p : list){
            sumCost = sumCost.add(p.getCost());
        }
        return sumCost;
    }

//    public Long getLastOrderCount(){
//        Order order = orderRepository.
//        return
//    }
}
