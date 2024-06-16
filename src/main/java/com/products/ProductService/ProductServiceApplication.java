package com.products.ProductService;

import com.products.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class ProductServiceApplication {

	@Autowired
	ProductRepository productRepo;
	public static void main(String[] args) {

		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
