package com.product.controller;

import com.commons.constants.GenericApiConstants;
import com.commons.constants.GenericConstants;
import com.commons.product.ProductDto;
import com.commons.utility.ResponseDto;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @PostMapping
    ResponseDto save(@RequestBody ProductDto productDto){
        ProductDto saved = productService.save(productDto);
        if(null != saved){
            return new ResponseDto(true,saved, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY,"added"));
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "add"));
    }

    @PutMapping
    ResponseDto update(@RequestBody ProductDto productDto){
        ProductDto updated = productService.update(productDto);
        if(null != updated){
            return new ResponseDto(true,updated, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY,"updated"));
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "update"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_BY_ID)
    ResponseDto getById(@RequestParam(name = "productId") String productId){
        ProductDto productDto = productService.getById(productId);
        if(null != productDto){
            return new ResponseDto(true,productDto,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "fetched") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchById"));
    }

    @DeleteMapping(GenericApiConstants.ENDPOINT_BY_ID)
    ResponseDto deleteById(@RequestParam(name = "productId") String productId){
        boolean success = productService.deleteById(productId);
        if(success){
            return new ResponseDto(true,productId,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "deleted") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "deleteById"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_GET_ALL)
    ResponseDto getAll(){
        List<ProductDto> productDtos = productService.getAll();
        if(!CollectionUtils.isEmpty(productDtos)){
            return new ResponseDto(true,productDtos,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "retrived") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchAll"));
    }
//
//    @PostMapping
//    ResponseDto save(@RequestBody ProductDto productDto){
//        ProductDto save = productService.save(productDto);
//        if(null != save){
//            return new ResponseDto(true,save, GenericConstants.RECORD_ADDED_SUCCESSFULLY);
//        }
//        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "add"));
//    }

}
