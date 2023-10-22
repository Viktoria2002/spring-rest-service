package com.spring.rest.service.impl;

import com.spring.rest.exception.ResourceNotFoundException;
import com.spring.rest.model.Order;
import com.spring.rest.model.Product;
import com.spring.rest.model.User;
import com.spring.rest.repository.OrderRepository;
import com.spring.rest.service.ProductService;
import com.spring.rest.service.UserService;
import com.spring.rest.service.dto.OrderDto;
import com.spring.rest.service.dto.SimpleOrderDto;
import com.spring.rest.service.impl.OrderServiceImpl;
import com.spring.rest.service.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private List<Order> orders;

    @BeforeEach
     void setUp() {
        User user = new User(1L, "LN", "FN", "email", "pass");

        Product product1 = new Product(1L, "product", "description", new BigDecimal(200));
        Product product2 = new Product(2L, "product2", "description2", new BigDecimal(300));

        order = new Order(1L, new Date(2023, 10, 10), new BigDecimal(0.2),
                "address", user);
        List<Product> productList1 = Arrays.asList(product1);
        order.setProducts(productList1);

        Order order2 = new Order(2L, new Date(2023, 10, 21), new BigDecimal(0.1),
                "address", user);
        List<Product> productList2 = Arrays.asList(product2);
        order2.setProducts(productList2);

        orders = Arrays.asList(order, order2);
    }

    @Test
    void testGetByNonExistingOrderId() {
        Long orderId = 999L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            orderService.getById(orderId);
        });
    }

    @Test
    void testGetByExistingOrderId() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order));
        OrderDto expectedDto = OrderMapper.INSTANCE.toDto(order);

        OrderDto actualDto = orderService.getById(1L);

        assertNotNull(actualDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testDeleteById() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order));

        orderService.deleteById(1L);

        verify(orderRepository).delete(order);
    }

    @Test
    void testFindAll() {
        int expectedDtosSize = 2;

        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> actualDtos = orderService.findAll();

        assertNotNull(actualDtos);
        assertEquals(expectedDtosSize, actualDtos.size());
    }

    @Test
    void testSaveOrUpdate() {
        SimpleOrderDto orderDto = new SimpleOrderDto();
        orderDto.setDate(new Date(2023, 10, 10));
        orderDto.setDiscount(new BigDecimal(0.2));
        orderDto.setShippingAddress("address");
        orderDto.setProductIds(Arrays.asList(1L));
        orderDto.setUserId(1L);

        when(orderRepository.save(any())).thenReturn(order);
        OrderDto expectedDto = OrderMapper.INSTANCE.toDto(order);

        OrderDto actualDto = orderService.saveOrUpdate(orderDto);

        assertEquals(expectedDto, actualDto);
    }
}