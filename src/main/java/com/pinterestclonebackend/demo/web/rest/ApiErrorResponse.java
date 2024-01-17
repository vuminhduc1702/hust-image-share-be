package com.pinterestclonebackend.demo.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
//Sẽ chuyển đổi thành kiểu JSON với những thuộc tính khác NULL
public class ApiErrorResponse {
    private String code;
    private String error;
    private String errorDescription;
    private Object data;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String code, String error, String errorDescription) {
        this.code = code;
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public ApiErrorResponse(String code, String error, String errorDescription, Object data) {
        this.code = code;
        this.error = error;
        this.errorDescription = errorDescription;
        this.data = data;
    }

    public ApiErrorResponse(ApiResponse apiResponse) {
        this.code = apiResponse.getCode();
        this.error = apiResponse.getError();
    }

    public ApiErrorResponse(String code, String error) {
        this.code = code;
        this.error = error;
    }
}

