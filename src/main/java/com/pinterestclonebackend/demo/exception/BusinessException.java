package com.pinterestclonebackend.demo.exception;

import com.pinterestclonebackend.demo.web.rest.ApiResponse;

import java.util.List;

public class BusinessException extends BaseException {
    List<String> params = null;

    public BusinessException() {
    }

    public BusinessException(String code, String message) {
        super(code, message);
    }

    public BusinessException(String code, String message, String messageDescription) {
        super(code, message, messageDescription);
    }

    public BusinessException(ApiResponse apiResponse) {
        super(apiResponse);
    }

    public BusinessException(ApiResponse apiResponse, List<String> params) {
        super(apiResponse);
        this.params = params;
    }

    public List<String> getParams() {
        return params;
    }

    public BusinessException(ApiResponse apiResponse, String errorDescription) {
        super(apiResponse);
        this.messageDescription = errorDescription;
    }

}

