package com.spring.rest.service.mapper;

import com.spring.rest.model.Product;
import com.spring.rest.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> products);
}
