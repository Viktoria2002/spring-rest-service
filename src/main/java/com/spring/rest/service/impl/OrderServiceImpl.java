package com.spring.rest.service.impl;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.Order;
import com.spring.rest.repository.OrderRepository;
import com.spring.rest.service.OrderService;
import com.spring.rest.service.ProductService;
import com.spring.rest.service.UserService;
import com.spring.rest.service.dto.OrderDto;
import com.spring.rest.service.dto.SimpleOrderDto;
import com.spring.rest.service.mapper.OrderMapper;
import com.spring.rest.service.mapper.SimpleOrderDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.rest.configuration.Constants.ExceptionMessages.ORDER_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;
    private UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, UserService userService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = findById(id);
        return OrderMapper.INSTANCE.toDto(order);
    }

    @Override
    public void deleteById(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.INSTANCE.toDto(orders);
    }

    @Override
    public OrderDto saveOrUpdate(SimpleOrderDto orderDto) {
        Order order = SimpleOrderDtoMapper.INSTANCE.dtoToEntity(orderDto, productService, userService);
        return OrderMapper.INSTANCE.toDto(orderRepository.save(order));
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }
}
