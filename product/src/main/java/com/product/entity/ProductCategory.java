package com.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product_category")
public class ProductCategory extends Auditable{

    @Id
    @Column(name = "product_category")
    private String productCategory;

    @Column(name="sub_category")
    private String subCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "tax_code")
    private String taxCode;

}
