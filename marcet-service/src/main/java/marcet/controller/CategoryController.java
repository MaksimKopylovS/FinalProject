package marcet.controller;

import lombok.RequiredArgsConstructor;
import marcet.model.ProductsCategory;
import marcet.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/categoriyes")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get-all")
    public List<ProductsCategory> getCategoriyes(){
        return categoryService.getCategoriyes();
    }
}
