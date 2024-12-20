package com.commons.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductDto {

    private String productId;
    private String productName;
    private String ownerId;
    private String productCategory;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal finalPrice;
}
