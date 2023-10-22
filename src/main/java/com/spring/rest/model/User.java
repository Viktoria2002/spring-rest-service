package com.spring.rest.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Field \"last_name\" must not be null")
    private String lastName;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Field \"first_name\" must not be null")
    private String firstName;
    @Column(name = "email", nullable = false)
    @NotBlank(message = "Field \"email\" must not be null")
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,8})+$", message = "Field \"email\" is invalid")
    private String email;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Field \"password\" must not be null")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public User() {
    }

    public User(Long id, String lastName, String firstName, String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public User(String lastName, String firstName, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, email, password);
    }
}
