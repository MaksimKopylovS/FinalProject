package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.model.ProductsCategory;
import marcet.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<ProductsCategory> getCategoriyes(){
        return categoryRepository.findAll();
    }
}
