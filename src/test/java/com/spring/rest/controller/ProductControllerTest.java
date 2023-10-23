package com.spring.rest.controller;

import com.spring.rest.model.Product;
import com.spring.rest.service.ProductService;
import com.spring.rest.service.dto.ProductDto;
import com.spring.rest.service.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testGetProduct() {
        long orderId = 1;
        ProductDto expectedDto = new ProductDto();
        expectedDto.setId(orderId);

        when(productService.getById(orderId)).thenReturn(expectedDto);

        ProductDto actualDto = productController.getProduct(orderId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testGetAllProducts() {
        List<ProductDto> expectedList = new ArrayList<>();
        expectedList.add(new ProductDto());
        expectedList.add(new ProductDto());

        when(productService.findAll()).thenReturn(expectedList);

        List<ProductDto> actualList = productController.getProducts();

        assertEquals(expectedList, actualList);
    }

    @Test
    void testSaveProduct() {
        Product Product = new Product("product1", "description1", new BigDecimal(200));
        ProductDto expectedProductDto = ProductMapper.INSTANCE.toDto(Product);

        when(productService.saveOrUpdate(Product)).thenReturn(expectedProductDto);

        ProductDto actualProductDto = productController.saveOrUpdateProduct(Product);

        assertEquals(expectedProductDto, actualProductDto);
    }

    @Test
    void testUpdateProduct() {
        Product Product = new Product(1L,"product1", "description1", new BigDecimal(200));
        ProductDto expectedProductDto = ProductMapper.INSTANCE.toDto(Product);

        when(productService.saveOrUpdate(Product)).thenReturn(expectedProductDto);

        ProductDto actualProductDto = productController.saveOrUpdateProduct(Product);

        assertEquals(expectedProductDto, actualProductDto);
    }

    @Test
    void testDeleteProduct() {
        long productId = 1;
        String expectedMessage = "Product with ID = " + productId + " was deleted";

        String actualMessage = productController.deleteProduct(productId);

        assertEquals(expectedMessage, actualMessage);
    }
}
