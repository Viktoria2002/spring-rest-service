package com.spring.rest.service.impl;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;
import com.spring.rest.service.ProductService;
import com.spring.rest.service.dto.ProductDto;
import com.spring.rest.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.rest.configuration.Constants.ExceptionMessages.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto saveOrUpdate(Product product) {
        return ProductMapper.INSTANCE.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = findById(id);
        return ProductMapper.INSTANCE.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.INSTANCE.toDto(products);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }
}
