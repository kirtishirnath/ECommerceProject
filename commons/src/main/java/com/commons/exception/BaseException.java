package com.commons.exception;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    public static final HttpStatus DEFAULT_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    public static final String DEFAULT_MESSAGE = "Something went wrong,Please try again!!!";

    private  HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseException(String message) {
        this(message,DEFAULT_MESSAGE);
    }
    public BaseException(String message,String defaultMessage){
        this.message = StringUtils.isBlank(message) ? defaultMessage : message;
    }
    public BaseException(HttpStatus httpStatus , String message){
        this(httpStatus,DEFAULT_STATUS,message,DEFAULT_MESSAGE);
    }

    public BaseException(HttpStatus httpStatus, HttpStatus defaultStatus,String message, String defaultMessage){
        this.httpStatus = httpStatus == null ? DEFAULT_STATUS : httpStatus;
        this.message = StringUtils.isBlank(message) ? DEFAULT_MESSAGE : message;
    }
}
