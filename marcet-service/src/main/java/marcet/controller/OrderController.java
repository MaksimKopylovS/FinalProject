package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.service.BasketService;
import marcet.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private static Long orderCount;
    private final OrderService orderService;


    @PostConstruct
    public void init(){
        orderCount = 0L;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody List<String> data) {
        orderCount++;
        orderService.createOrder(data, orderCount);

    }

}
