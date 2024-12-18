package com.commons.utility;

import com.commons.product.ProductDto;
import lombok.Data;

@Data
public class ResponseDto {
    private boolean isSuccess;
    private Object data;
    private String message;

    public ResponseDto(boolean isSuccess, Object object, String message) {
        this.isSuccess=isSuccess;
        this.data=object;
        this.message=message;
    }
}
