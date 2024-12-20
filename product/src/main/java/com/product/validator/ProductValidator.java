package com.product.validator;

import com.commons.product.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {
    public void validateProduct(ProductDto productDto){
        validateOwner(productDto.getOwnerId());
    }

    private void validateOwner(String ownerId) {

    }
}
