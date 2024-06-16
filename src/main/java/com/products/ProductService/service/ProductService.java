package com.products.ProductService.service;

import com.products.ProductService.model.Product;
import com.products.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product productToSave){
        System.out.println("adding product");
        return productRepository.save(productToSave);
    }
    public List<Product> getAllProducts(){

        return productRepository.findAll();
    }
    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

}
