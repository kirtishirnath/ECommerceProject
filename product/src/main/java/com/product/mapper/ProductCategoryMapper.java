package com.product.mapper;

import com.commons.product.ProductCategoryDto;
import com.product.entity.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory);

    ProductCategory productCategoryDtoToProductCategory(ProductCategoryDto productCategoryDto);
}
