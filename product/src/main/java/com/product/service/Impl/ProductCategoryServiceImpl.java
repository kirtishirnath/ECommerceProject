package com.product.service.Impl;

import com.commons.product.ProductCategoryDto;
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
        return productCategoryMapper.productCategoryToProductCategoryDto(productCategoryRepository
                .save(productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto)));
    }

    @Override
    public ProductCategoryDto update(ProductCategoryDto productCategoryDto) {
        if (StringUtils.isNotBlank(productCategoryDto.getProductCategory()) && productCategoryRepository.existsById(productCategoryDto.getProductCategory())) {
            return productCategoryMapper.productCategoryToProductCategoryDto(productCategoryRepository
                    .save(productCategoryMapper.productCategoryDtoToProductCategory(productCategoryDto)));
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
}
