package com.product.controller;

import com.commons.constants.GenericApiConstants;
import com.commons.constants.GenericConstants;
import com.commons.product.ProductCategoryDto;
import com.commons.product.ProductDto;
import com.commons.utility.ResponseDto;
import com.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping
    ResponseDto save(@RequestBody ProductCategoryDto productCategoryDto) {
        ProductCategoryDto saved = productCategoryService.save(productCategoryDto);
        if (null != saved) {
            return new ResponseDto(true, saved, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "added"));
        }
        return new ResponseDto(false, null, String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "add"));

    }

    @PutMapping
    ResponseDto update(@RequestBody ProductCategoryDto productCategoryDto) {
        ProductCategoryDto updated = productCategoryService.update(productCategoryDto);
        if (null != updated) {
            return new ResponseDto(true, updated, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "updated"));
        }
        return new ResponseDto(false, null, String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "update"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_BY_ID)
    ResponseDto getById(@RequestParam(name = "productCategory") String productCategory) {
        ProductCategoryDto productCategoryDto = productCategoryService.getById(productCategory);
        if (null != productCategoryDto) {
            return new ResponseDto(true, productCategoryDto, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "fetched"));
        }
        return new ResponseDto(false, null, String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchById"));
    }

    @DeleteMapping(GenericApiConstants.ENDPOINT_BY_ID)
    ResponseDto deleteById(@RequestParam(name = "productCategory") String productCategory) {
        boolean success = productCategoryService.deleteById(productCategory);
        if (success) {
            return new ResponseDto(true, productCategory, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "deleted"));
        }
        return new ResponseDto(false, null, String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "deleteById"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_GET_ALL)
    ResponseDto getAll() {
        List<ProductCategoryDto> productCategoryDtos = productCategoryService.getAll();
        if (!CollectionUtils.isEmpty(productCategoryDtos)) {
            return new ResponseDto(true, productCategoryDtos, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "retrieved"));
        }
        return new ResponseDto(false, null, String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchAll"));
    }

}
