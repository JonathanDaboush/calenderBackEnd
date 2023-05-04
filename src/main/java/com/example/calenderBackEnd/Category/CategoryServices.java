package com.example.calenderBackEnd.Category;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class CategoryServices {
    CategoryRepository categoryRepository;
    public Category getcategoryById(long id){
        return categoryRepository.getReferenceById(id);
    }
    public void savecategory(Category category){
        categoryRepository.save(category);
    }
    public void removecategory(long id){
        categoryRepository.deleteById(id);
    }
    public List<Category> getcategorys(){
        return categoryRepository.findAll();
    }
}
