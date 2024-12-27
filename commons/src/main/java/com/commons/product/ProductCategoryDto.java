package com.commons.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private String productCategoryCode;

    private String name;

    private String discountCode;

    private String taxCode;
}
