package com.products.ProductService.controller;

import com.products.ProductService.model.Product;
import com.products.ProductService.repository.ProductRepository;
import com.products.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        System.out.println("adding product");
        return productService.saveProduct(product);
    }
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();

    }

}
