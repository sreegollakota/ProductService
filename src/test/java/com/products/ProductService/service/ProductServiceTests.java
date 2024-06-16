package com.products.ProductService.service;

import com.products.ProductService.model.Product;
import com.products.ProductService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0").withExposedPorts(27017);

	@DynamicPropertySource
	static void mongoDbProperties(DynamicPropertyRegistry registry) {

		mongoDBContainer.start();
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	@Autowired
	private ProductService productService;

	@Test
	public void givenProduct_whenSave_thenProductIsSaved() {


		Product product = new Product("1","product1", 100,"TestCategory","MyDescription");
		Product savedProduct = productService.saveProduct(product);
		assertEquals(product.getName(), savedProduct.getName());

		Product foundProduct = productService.findById("1");
		assertEquals(product.getName(), foundProduct.getName());
	}

	@Test
	public void givenProduct_whenDelete_thenProductIsDeleted() {
		Product product = new Product("1","product1", 100,"TestCategory","MyDescription");
		Product savedProduct = productService.saveProduct(product);
		assertEquals(product.getName(), savedProduct.getName());

		productService.deleteById("1");
		assertThrows(RuntimeException.class, () -> productService.findById("1"));
	}

	@Test
	public void givenProduct_whenFindAll_thenProductIsFound() {
		Product product = new Product("1","product1", 100,"TestCategory","MyDescription");
		Product savedProduct = productService.saveProduct(product);
		assertEquals(product.getName(), savedProduct.getName());

		Product product2 = new Product("2","product2", 100,"TestCategory","MyDescription");
		Product savedProduct2 = productService.saveProduct(product2);
		assertEquals(product2.getName(), savedProduct2.getName());

		assertEquals(2, productService.findAll().size());
	}
}
