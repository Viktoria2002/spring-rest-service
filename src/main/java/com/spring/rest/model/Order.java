package com.spring.rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;
    @Column(name = "shipping_address", nullable = false)
    @NotBlank(message = "Field \"shipping_address\" must not be null")
    private String shippingAddress;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "product_order"
            , joinColumns = @JoinColumn(name = "order_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {

    }

    public Order(Long id, Date date, BigDecimal discount, String shippingAddress, User user) {
        this.id = id;
        this.date = date;
        this.discount = discount;
        this.shippingAddress = shippingAddress;
        this.user = user;
    }

    public Order(Date date, BigDecimal discount, String shippingAddress, User user) {
        this.date = date;
        this.discount = discount;
        this.shippingAddress = shippingAddress;
        this.user = user;
    }

    public BigDecimal countTotalAmount() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(discount, order.discount) && Objects.equals(shippingAddress, order.shippingAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, discount, shippingAddress);
    }
}
