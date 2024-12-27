package com.user.service;

import com.commons.product.ProductDto;

import java.util.List;

public interface GenericService<T> {
    T save(T object);

    T update(T object);

    T getById(String object);

    boolean deleteById(String object);

    List<ProductDto> getAll();
}
