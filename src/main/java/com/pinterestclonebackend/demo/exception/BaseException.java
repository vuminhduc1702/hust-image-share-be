package com.pinterestclonebackend.demo.exception;

import lombok.Data;
import com.pinterestclonebackend.demo.web.rest.ApiResponse;

@Data
public class BaseException extends RuntimeException {

    protected String code;

    protected String message;

    protected  String messageDescription;


    public BaseException() {

    }

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(String code, String message, String messageDescription) {
        super(message);
        this.code = code;
        this.message = message;
        this.messageDescription = messageDescription;
    }

    public BaseException(ApiResponse apiResponse) {
        this.code = apiResponse.getCode();
        this.message = apiResponse.getError();
    }
}
