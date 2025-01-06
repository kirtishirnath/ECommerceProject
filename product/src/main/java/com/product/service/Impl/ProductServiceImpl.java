package com.product.service.Impl;

import com.commons.product.ProductDto;
import com.commons.utility.PageableResponse;
import com.commons.utility.SearchRequest;
import com.product.entity.Product;
import com.product.mapper.ProductMapper;
import com.product.repo.ProductRepository;
import com.product.service.ProductService;
import com.product.service.ProductUtilsService;
import com.product.validator.ProductValidator;
import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductValidator productValidator;

    @Autowired
    ProductUtilsService productUtilsService;

    @Override
    public ProductDto save(ProductDto productDto) {
       productValidator.validateProduct(productDto);
       return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDto)));
    }

    @Override
    public ProductDto update(ProductDto productDto) {
      if(!StringUtils.isBlank(productDto.getProductId()) && productRepository.existsById(productDto.getProductId()) ){
          productValidator.validateProduct(productDto);
          return productMapper.productToProductDto(productRepository.saveAndFlush(productMapper.productDtoToProduct(productDto)));
      }
      return null;
    }


    @Override
    public ProductDto getById(String productId) {
       return productMapper.productToProductDto(productRepository.findByProductId(productId));
    }


    @Override
    public boolean deleteById(String productId) {
       if(productRepository.existsById(productId)){
           productRepository.deleteById(productId);
           return true;
       }
       return false;
    }

    @Override
    public List<ProductDto> getAll() {
        return productMapper.productListToProductDtoList(productRepository.findAll());
    }
    @Override
    public PageableResponse<ProductDto> search(SearchRequest searchRequest) {
        PageRequest pageRequest = productUtilsService.preparePageableRequest(searchRequest);
        String filter = !StringUtils.isBlank(searchRequest.getFilter()) ? searchRequest.getFilter() : Strings.EMPTY;
        return buildPageableResponse(productRepository.findAllProducts(filter.toUpperCase(),pageRequest));

    }

    private PageableResponse<ProductDto> buildPageableResponse(Page<Product> allProducts) {
        PageableResponse pageableResponse = new PageableResponse();
        pageableResponse.setCurrentPage(allProducts.getNumber());
        pageableResponse.setTotalPages(allProducts.getTotalPages());
        pageableResponse.setTotalRecord(allProducts.getTotalElements());
        pageableResponse.setData(productMapper.productListToProductDtoList(allProducts.getContent()));
        return pageableResponse;
    }
}
