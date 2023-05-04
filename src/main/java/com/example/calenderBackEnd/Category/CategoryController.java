package com.example.calenderBackEnd.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @GetMapping
    public List<Category> getcategorys() {
        return categoryServices.getcategorys();
    }
    @Autowired
    CategoryServices categoryServices;
    @GetMapping("/{id}")
    public Category getcategory(@PathVariable Long id) throws IOException {
        Category category=categoryServices.getcategoryById(id);
        return category;
    }
    @PostMapping
    public ResponseEntity createcategory(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        Category category=new Category();
        try{
            category=categoryServices.getcategoryById((long)payLoad.get("id"));
        }
        catch(Exception e){

        }

        category.setName((String)payLoad.get("name"));

        categoryServices.savecategory(category);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletecategory(@PathVariable Long id) {
        categoryServices.removecategory(id);
        return ResponseEntity.ok().build();
    }
}
