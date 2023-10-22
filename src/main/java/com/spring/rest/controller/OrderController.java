package com.spring.rest.controller;

import com.spring.rest.service.OrderService;
import com.spring.rest.service.dto.OrderDto;
import com.spring.rest.service.dto.SimpleOrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.rest.configuration.Constants.Messages.ORDER_DELETED_MESSAGE;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/find/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/findAll")
    public List<OrderDto> getOrders() {
        return orderService.findAll();
    }

    @PostMapping("/save")
    public OrderDto saveOrUpdateOrder(@RequestBody SimpleOrderDto order) {
        return orderService.saveOrUpdate(order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return String.format(ORDER_DELETED_MESSAGE, id);
    }
}
