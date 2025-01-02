package com.product.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


import java.math.BigDecimal;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "product")
public class Product extends Auditable{

    @Id
    @Column(name = "product_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="brand")
    private String brand;

    @Column(name = "seller_id")
    private String sellerId;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "tax_code")
    private String taxCode;

}
