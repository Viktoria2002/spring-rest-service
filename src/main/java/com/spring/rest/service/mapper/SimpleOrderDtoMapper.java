package com.spring.rest.service.mapper;

import com.spring.rest.model.Order;
import com.spring.rest.model.Product;
import com.spring.rest.model.User;
import com.spring.rest.service.ProductService;
import com.spring.rest.service.UserService;
import com.spring.rest.service.dto.SimpleOrderDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface SimpleOrderDtoMapper {
    SimpleOrderDtoMapper INSTANCE = Mappers.getMapper(SimpleOrderDtoMapper.class);

    @Mapping(target = "products", source = "productIds", qualifiedByName = "mapToProducts")
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapToUser")
    Order dtoToEntity(SimpleOrderDto orderDto, @Context ProductService productService, @Context UserService userService);

    @Named("mapToProducts")
    default List<Product> mapToProducts(List<Long> productIds, @Context ProductService productService) {
        if (productIds == null) {
            return new ArrayList<>();
        } else {
            return productIds.stream()
                    .map(productService::findById)
                    .collect(Collectors.toList());
        }
    }

    @Named("mapToUser")
    default User mapToUser(Long userId, @Context UserService userService) {
        if (userId == null) {
            return null;
        } else {
            return userService.findById(userId);
        }
    }
}
