package com.spring.rest.controller;

import com.spring.rest.model.Product;
import com.spring.rest.service.ProductService;
import com.spring.rest.service.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.rest.configuration.Constants.Messages.PRODUCT_DELETED_MESSAGE;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/findAll")
    public List<ProductDto> getProducts() {
        return productService.findAll();
    }

    @PostMapping("/save")
    public ProductDto saveOrUpdateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return String.format(PRODUCT_DELETED_MESSAGE, id);
    }
}
