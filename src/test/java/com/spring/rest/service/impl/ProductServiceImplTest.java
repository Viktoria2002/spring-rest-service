package com.spring.rest.service.impl;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;
import com.spring.rest.service.dto.ProductDto;
import com.spring.rest.service.impl.ProductServiceImpl;
import com.spring.rest.service.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    private List<Product> products;

    @BeforeEach
    public void setUp() {
        product = new Product(1L, "product1", "description1", new BigDecimal(200));
        Product product2 = new Product(2L, "product2", "description2", new BigDecimal(300));
        products = Arrays.asList(product, product2);
    }

    @Test
    void testGetByNonExistingUserId() {
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getById(productId);
        });
    }

    @Test
    void testGetByExistingUserId() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));
        ProductDto expectedDto = ProductMapper.INSTANCE.toDto(product);

        ProductDto actualDto = productService.getById(1L);

        assertNotNull(actualDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testDeleteById() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product));

        productService.deleteById(1L);

        verify(productRepository).delete(product);
    }

    @Test
    void testFindAll() {
        int expectedDtosSize = 2;

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> actualDtos = productService.findAll();

        assertNotNull(actualDtos);
        assertEquals(expectedDtosSize, actualDtos.size());
    }

    @Test
    void testSaveOrUpdate() {
        when(productRepository.save(any())).thenReturn(product);
        ProductDto expectedDto = ProductMapper.INSTANCE.toDto(product);

        ProductDto actualDto = productService.saveOrUpdate(product);

        assertEquals(expectedDto, actualDto);
    }
}