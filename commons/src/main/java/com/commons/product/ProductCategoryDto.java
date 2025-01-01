package com.commons.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductCategoryDto extends AuditableDto {

    private String productCategory;

    private String name;

    private String discountCode;

    private String taxCode;
}
