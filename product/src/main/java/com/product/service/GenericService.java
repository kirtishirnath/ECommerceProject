package com.product.service;

import com.commons.product.ProductDto;

public interface GenericService<T> {

    T save(T object);

    T update(T object);

    T getById(T object);

    T deleteById(T object);
}
