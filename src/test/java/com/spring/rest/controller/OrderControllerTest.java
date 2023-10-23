package com.spring.rest.controller;

import com.spring.rest.service.OrderService;
import com.spring.rest.service.dto.OrderDto;
import com.spring.rest.service.dto.SimpleOrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void testGetOrder() {
        long orderId = 1;
        OrderDto expectedOrderDto = new OrderDto();
        expectedOrderDto.setId(orderId);

        when(orderService.getById(orderId)).thenReturn(expectedOrderDto);

        OrderDto actualOrderDto = orderController.getOrder(orderId);

        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test
    void testGetAllOrders() {
        List<OrderDto> expectedOrderList = new ArrayList<>();
        expectedOrderList.add(new OrderDto());
        expectedOrderList.add(new OrderDto());

        when(orderService.findAll()).thenReturn(expectedOrderList);

        List actualOrderList = orderController.getOrders();

        assertEquals(expectedOrderList, actualOrderList);
    }

    @Test
    void testSaveOrder() {
        SimpleOrderDto simpleOrderDto = new SimpleOrderDto();
        simpleOrderDto.setDate(new Date(2023, 7, 8));
        OrderDto expectedOrderDto = new OrderDto();
        expectedOrderDto.setDate(new Date(2023, 7, 8));

        when(orderService.saveOrUpdate(simpleOrderDto)).thenReturn(expectedOrderDto);

        OrderDto actualOrderDto = orderController.saveOrUpdateOrder(simpleOrderDto);

        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test
    void testUpdateOrder() {
        SimpleOrderDto simpleOrderDto = new SimpleOrderDto();
        simpleOrderDto.setId(1L);
        simpleOrderDto.setDate(new Date(2023, 7, 8));
        OrderDto expectedOrderDto = new OrderDto();
        expectedOrderDto.setId(1L);
        expectedOrderDto.setDate(new Date(2023, 7, 8));

        when(orderService.saveOrUpdate(simpleOrderDto)).thenReturn(expectedOrderDto);

        OrderDto actualOrderDto = orderController.saveOrUpdateOrder(simpleOrderDto);

        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test
    void testDeleteOrder() {
        long orderId = 1;
        String expectedMessage = "Order with ID = " + orderId + " was deleted";

        String actualMessage = orderController.deleteOrder(orderId);

        assertEquals(expectedMessage, actualMessage);
    }
}
