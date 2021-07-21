package marcet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/create")
    public void createOrder(@RequestBody String adres, String username) {
        System.out.println(username + " " + adres);
    }

}
