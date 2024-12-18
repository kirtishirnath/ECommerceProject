package com.commons.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private String productId;
    private String ownerId;
    private String productCategory;
    private BigDecimal price;
    private BigDecimal finalPrice;
}
