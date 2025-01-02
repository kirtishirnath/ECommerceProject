package com.product.repo;

import com.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,String> {
    ProductCategory findByProductCategory(String productCategory);
}
