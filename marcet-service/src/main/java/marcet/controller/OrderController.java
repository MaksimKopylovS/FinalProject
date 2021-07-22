package marcet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/create")
    public void createOrder(@RequestBody List<String> data) {
        for(String str : data){
            System.out.println(str);
        }
    }

}
