package com.product.validator;

import com.commons.product.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validateProduct(ProductDto productDto){
        validateOwner(productDto.getSellerId());
    }

    private void validateOwner(String sellerId) {

    }
}
