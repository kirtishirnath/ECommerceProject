package com.product.repo;

import com.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,String> {

    Product findByProductId(String productId);

    @Query(value = """
            select
            	*
            from
            	product.product p
            where
            	:filter is null
            	or :filter = ''
            	or upper(p.product_name) like %:filter%
            	or upper(p.product_category) like %:filter%
            """,nativeQuery = true)
    Page<Product> findAllProducts(String filter, PageRequest pageRequest);
}
