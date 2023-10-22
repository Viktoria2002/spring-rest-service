package com.spring.rest.service;


import com.spring.rest.model.Order;
import com.spring.rest.service.dto.OrderDto;
import com.spring.rest.service.dto.SimpleOrderDto;

public interface OrderService extends Service<OrderDto, Order, Long>{
    OrderDto saveOrUpdate(SimpleOrderDto order);
}
