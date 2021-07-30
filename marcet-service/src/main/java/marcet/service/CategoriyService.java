package marcet.service;

import lombok.RequiredArgsConstructor;
import marcet.model.ProductsCategoriy;
import marcet.repository.CategoriyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriyService {

    private final CategoriyRepository categoriyRepository;

    public List<ProductsCategoriy> getCategoriyes(){
        return categoriyRepository.findAll();
    }
}
