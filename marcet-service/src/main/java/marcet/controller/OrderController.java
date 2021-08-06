package marcet.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.OrderShowDTO;
import marcet.model.JwtResponse;
import marcet.model.Order;
import marcet.service.BasketService;
import marcet.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private static Long orderCount;
    private final BasketService basketService;
    private final OrderService orderService;

    @PostConstruct
    public void init(){
        orderCount = 0L;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody String userName) {
        log.info("Username - {}", userName);
        return ResponseEntity.ok(basketService.createOrder(userName));
    }

    @PostMapping("/show")
    public ResponseEntity<?> showOrderOnNumber(@RequestBody Long orderNumber){
        log.info("OrderNumber {}", orderNumber);
        return ResponseEntity.ok(orderService.showOrderOnNumber(orderNumber));
    }
}
