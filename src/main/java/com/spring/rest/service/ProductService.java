package com.spring.rest.service;

import com.spring.rest.model.Product;
import com.spring.rest.service.dto.ProductDto;

public interface ProductService extends Service<ProductDto, Product, Long> {
    ProductDto saveOrUpdate(Product product);
}
