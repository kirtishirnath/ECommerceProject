package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductCategory {

    @Id
    private String productCategoryCode;

    private String name;

    private String discountCode;

    private String taxCode;

}
