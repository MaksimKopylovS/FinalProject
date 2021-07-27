package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.dto.ProductDTO;
import marcet.model.Order;
import marcet.model.User;
import marcet.repository.OrderRepository;
import marcet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final UserRepository userRepository;

    public void createOrder(List<String> data, Long countOrder){
        Order order = new Order();
        int sumCost = getSumCost(basketService.getBasket());
        System.out.println(data.get(1));
        User user = userRepository.findByUsername(data.get(1)).get();
        System.out.println(user.getUsername());
        for(ProductDTO p:basketService.getBasket()){
            order.setIdUser(user.getId());
            order.setIdProduct(p.getId());
            order.setOrderNumber(countOrder);
            order.setSumCost(sumCost);
            order.setAdres(data.get(0));
            System.out.println(order.getIdUser() + " " +
                    order.getIdProduct() + " " +
                    order.getOrderNumber() + " " +
                    order.getSumCost() + " " +
                    order.getAdres());
            //orderRepository.save(order);
        }

    }


    public int getSumCost(List<ProductDTO> list){
        int sumCost = 0;
        for(ProductDTO p : list){
            sumCost = sumCost + p.getCost();
        }
        return sumCost;
    }
}
