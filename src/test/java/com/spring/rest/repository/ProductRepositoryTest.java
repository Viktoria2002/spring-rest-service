package com.spring.rest.repository;

import com.spring.rest.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(JpaConfigTest.class)
@ExtendWith(PostgreSQLExtension.class)
@Transactional
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindAll() {
        int expectedProductsSize = 4;

        List<Product> products = productRepository.findAll();

        assertEquals(expectedProductsSize, products.size());
    }

    @Test
    void testFindById() {
        String expectedName = "product2";

        Optional<Product> product = productRepository.findById(2L);

        assertTrue(product.isPresent());
        assertEquals(expectedName, product.get().getName());
    }

    @Test
    void testSave() {
        Product product = new Product(5L, "product5", "description5", new BigDecimal(500));

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
    }

    @Test
    void testUpdate() {
        Product product = new Product(2L, "newProduct", "newDescription", new BigDecimal(600));

        Product updatedProduct = productRepository.save(product);

        assertEquals(product, updatedProduct);
    }

    @Test
    void testDelete() {
        Product product = new Product(3L, "product3", "description3", new BigDecimal(400));

        assertTrue(productRepository.findById(3L).isPresent());

        productRepository.delete(product);

        assertFalse(productRepository.findById(3L).isPresent());
    }
}
