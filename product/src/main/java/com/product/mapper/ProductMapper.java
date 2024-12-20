package com.product.mapper;

import com.commons.product.ProductDto;
import com.product.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    List<ProductDto> productListToProductDtoList(List<Product> products);
    List<Product> productDtoListToProductList(List<ProductDto> productDtos);
}
