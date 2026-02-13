package ee.eljas.veebipood.controller;

import ee.eljas.veebipood.entity.Product;
import ee.eljas.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {

    //localhost: 8080/products
    //application.prroperties server.port=8090
//    @GetMapping("products")
//    public String helloworld(){
//        return "Hello World";
//    }
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow() ;
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id); //Kustutan
        return productRepository.findAll(); //Uuenenud seis
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        if(product.getId()!=null){
            throw new RuntimeException("Cannot add without ID");
        }
        productRepository.save(product); //Siin salvestab
        return productRepository.findAll(); //Siin on uuenenud seis
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product){
        if(product.getId()==null){
            throw new RuntimeException("Cannot edit without ID");
        }
        productRepository.save(product); //Siin salvestab
        return productRepository.findAll(); //Siin on uuenenud seis
    }

}
