package com.spring.rest.service.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderDto {
    private Long id;
    private Date date;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private List<ProductDto> products;
    private UserDto user;


    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public UserDto getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(date, orderDto.date) && Objects.equals(totalAmount, orderDto.totalAmount) && Objects.equals(shippingAddress, orderDto.shippingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, totalAmount, shippingAddress);
    }
}
