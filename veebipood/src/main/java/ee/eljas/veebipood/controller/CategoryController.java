package ee.eljas.veebipood.controller;

import ee.eljas.veebipood.entity.Category;
import ee.eljas.veebipood.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories") // Defines the base path for all methods in this class
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Category> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id); // Kustutan
        return categoryRepository.findAll(); // Uuenenud seis
    }

    @PostMapping
    public List<Category> addCategory(@RequestBody Category category){
        categoryRepository.save(category); // Siin salvestab
        return categoryRepository.findAll(); // Siin on uuenenud seis
    }
}
