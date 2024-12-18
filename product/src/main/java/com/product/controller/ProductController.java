package com.product.controller;

import com.commons.constants.GenericConstants;
import com.commons.product.ProductDto;
import com.commons.utility.ResponseDto;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {


    @Autowired
    ProductService productService;

    @PostMapping
    ResponseDto save(@RequestBody ProductDto productDto){
        ProductDto save = productService.save(productDto);
        if(null != save){
            new ResponseDto(true,save, GenericConstants.RECORD_ADDED_SUCCESSFULLY);
        }
        return new ResponseDto(false,null,GenericConstants.ERROR_WHILE_ADDING_RECORD);
    }

}
