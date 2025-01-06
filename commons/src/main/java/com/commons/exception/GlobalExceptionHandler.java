package com.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<Object> handleException(BaseException ex){
        Map<String,Object> errorResponse = new HashMap<>();
        errorResponse.put("statusCode",ex.getHttpStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : ex.getHttpStatus());
        errorResponse.put("message",ex.getMessage());
        errorResponse.put("status",Boolean.FALSE);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
