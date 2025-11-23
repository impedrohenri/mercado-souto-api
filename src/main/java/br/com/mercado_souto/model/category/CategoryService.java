package br.com.mercado_souto.model.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercado_souto.util.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category create(Category category){
        category.setActive(Boolean.TRUE);
        return categoryRepository.save(category);
    }
    
    public List<Category> findAll(){
        return categoryRepository.findAll();

    }

    public Category findById(Long id){
        Category category= categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Category",id));

        return category;
    }

    @Transactional
    public Category update(Long id, Category modifiedCategory){
        Category category = findById(id);
        
        category.setName(modifiedCategory.getName());

        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long id){
        Category category = findById(id);
        category.setActive(Boolean.FALSE);

        categoryRepository.save(category);
    }
    
}
