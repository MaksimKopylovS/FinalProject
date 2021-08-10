package marcet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.OrderShowDTO;
import marcet.dto.ProductDTO;
import marcet.model.Address;
import marcet.model.Order;
import marcet.model.User;
import marcet.repository.AddressRepository;
import marcet.repository.OrderRepository;
import marcet.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Scope(scopeName = "prototype")
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;

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


    public BigDecimal getSumCost(List<ProductDTO> list) {
        BigDecimal sumCost = new BigDecimal(0);
        for (ProductDTO p : list) {
            sumCost = sumCost.add(p.getPrice());
        }
        return sumCost;
    }

    @Transactional
    public List<OrderShowDTO> showOrderOnNumber(Long orderNumber) {

        String query = "select order_items_tbl.order_id, products_tbl.title_fld," +
                " order_items_tbl.quantity_fld, products_tbl.price_fld, order_items_tbl.cost_fld" +
                " from order_items_tbl" +
                " inner join products_tbl on order_items_tbl.product_id = products_tbl.product_id" +
                " where order_id = '" + orderNumber + "'";
        List<Object[]> list = entityManager.createNativeQuery(query).getResultList();
        List<OrderShowDTO> orderShowDTOList = new ArrayList<>();
        for (Object[] objects : list) {
            orderShowDTOList.add(new OrderShowDTO(
                    (BigInteger)objects[0],
                    (String) objects[1],
                    (Short) objects[2],
                    (BigDecimal) objects[3],
                    (BigDecimal) objects[4])
            );
        }
        return orderShowDTOList;
    }



//    public Long getLastOrderCount(){
//        Order order = orderRepository.
//        return
//    }
}
