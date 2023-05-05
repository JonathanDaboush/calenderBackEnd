package com.example.calenderBackEnd.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class CategoryServices {
    @Autowired
   CategoryRepository categoryRepository;
    public Category getcategoryById(long id){
        Category category=categoryRepository.findById(id+1).get();
        return category;
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
