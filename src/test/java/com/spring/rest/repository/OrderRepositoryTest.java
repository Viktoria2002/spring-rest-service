package com.spring.rest.repository;

import com.spring.rest.model.Order;
import com.spring.rest.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(JpaConfigTest.class)
@ExtendWith(PostgreSQLExtension.class)
@Transactional
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAll() {
        int expectedOrdersSize = 3;

        List<Order> Orders = orderRepository.findAll();

        assertEquals(expectedOrdersSize, Orders.size());
    }

    @Test
    void testFindById() {
        Long expectedId = 2L;

        Optional<Order> order = orderRepository.findById(2L);

        assertTrue(order.isPresent());
        assertEquals(expectedId, order.get().getId());
    }

    @Test
    void testSave() {
        Order order = new Order(new Date(), new BigDecimal(0.1), "address4", new User());

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());
    }

    @Test
    void testUpdate() {
        Order order = new Order(2L, new Date(), new BigDecimal(0.3), "newAddress", userRepository.findById(1L).get());

        Order updatedOrder = orderRepository.save(order);

        assertEquals(order, updatedOrder);
    }

    @Test
    void testDelete() {
        Order order = new Order(2L, new Date(2023, 11, 11), new BigDecimal(0.02), "address2", userRepository.findById(1L).get());

        assertTrue(orderRepository.findById(2L).isPresent());

        orderRepository.delete(order);

        assertFalse(orderRepository.findById(2L).isPresent());
    }
}
