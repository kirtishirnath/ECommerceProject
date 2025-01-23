package com.product.service.Impl;

import com.commons.exception.BaseException;
import com.commons.product.ProductCategoryDto;
import com.commons.utility.PageableResponse;
import com.commons.utility.SearchRequest;
import com.product.mapper.ProductCategoryMapper;
import com.product.repo.ProductCategoryRepository;
import com.product.service.ProductCategoryService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategoryDto save(ProductCategoryDto productCategoryDto) {
        if(StringUtils.isNotBlank(productCategoryDto.getProductCategory())){
            if( !productCategoryRepository.existsById(productCategoryDto.getProductCategory())){
                return productCategoryMapper.productCategoryToProductCategoryDto(productCategoryRepository
                        .save(productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto)));
            }
            throw new BaseException("Product category already exists.");
        }
        return null;
    }

    @Override
    public ProductCategoryDto update(ProductCategoryDto productCategoryDto) {
        if (StringUtils.isNotBlank(productCategoryDto.getProductCategory())) {
            if(productCategoryRepository.existsById(productCategoryDto.getProductCategory())){
                return productCategoryMapper.productCategoryToProductCategoryDto(productCategoryRepository
                        .save(productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto)));
            }
            throw new BaseException("Product category does not exists.");
        }
        return null;
    }

    @Override
    public ProductCategoryDto getById(String productCategory) {
        if (StringUtils.isNotBlank(productCategory)) {
            return productCategoryMapper.productCategoryToProductCategoryDto(productCategoryRepository.findByProductCategory(productCategory));
        }
        return null;
    }

    @Override
    public boolean deleteById(String productCategory) {
        if (StringUtils.isNotBlank(productCategory) && productCategoryRepository.existsById(productCategory)) {
            productCategoryRepository.deleteById(productCategory);
            return true;
        }
        return false;
    }

    @Override
    public List<ProductCategoryDto> getAll() {
        return productCategoryMapper.productCategoryListToProductCategoryDtoList(productCategoryRepository.findAll());
    }

    @Override
    public PageableResponse<ProductCategoryDto> search(SearchRequest searchRequest) {
        return null;
    }
}
