package com.commons.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ProductDto extends AuditableDto {

    private String productId;
    private String productName;
    private String productDescription;
    private String brand;
    private String sellerId;
    private String productCategory;
    private BigDecimal price;
    private BigDecimal discount;
    private String taxCode;
}
