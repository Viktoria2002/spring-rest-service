package com.spring.rest.service;

import java.util.List;

public interface Service<T, F, K> {
    T getById(K id);

    F findById(K id);

    void deleteById(K id);

    List<T> findAll();
}
