package com.jordan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jordan.model.Product;
import com.jordan.repository.ProductRepository;
import com.jordan.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest
{
	@Autowired
	ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllProductsTest()
	{
		when(productRepository.findAll()).thenReturn(Stream
				.of(new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image"),
						new Product(2, "Dell Laptop", "Dell Laptop Next Gen", 1299.99f, 20, "Laptop", "Image"))
				.collect(Collectors.toList()));
		assertEquals(2, productService.getAllProducts().size());
	}

	@Test
	void getProductByIdTest()
	{
		int id = 1;
		Product product1 = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");
		Optional<Product> product = Optional.of(product1);
		when(productRepository.findById(id)).thenReturn(product);
		assertThat(product).isNotNull();
		assertEquals(product1, productService.getProductById(id).get());
	}

	@Test
	void saveProductTest()
	{
		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");

		productService.save(product);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	void deleteProductTest()
	{
		Product product = new Product(1, "Apple Laptop", "Apple Laptop Next Gen", 1099.99f, 20, "Laptop", "Image");

		productService.deleteProduct(1);
		verify(productRepository, times(1)).deleteById(1);
	}
}