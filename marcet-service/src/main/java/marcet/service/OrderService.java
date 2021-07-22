package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.model.Order;
import marcet.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(List<String> data){
        Order order = new Order();

        //orderRepository.save()

    }
}
