package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    private String productId;

    private String ownerId;

    private String productCategory;

    private BigDecimal price;

    private BigDecimal finalPrice;

}
