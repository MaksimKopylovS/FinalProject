package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.model.Categoriy;
import marcet.service.CategoriyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/categoriyes")
public class CategoriyController {

    private final CategoriyService categoriyService;

    @GetMapping("/get-all")
    public List<Categoriy> getCategoriyes(){
        System.out.println("Пришёл");
        return categoriyService.getCategoriyes();
    }
}
