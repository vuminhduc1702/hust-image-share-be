package com.pinterestclonebackend.demo.constants;

import com.pinterestclonebackend.demo.web.rest.ApiResponse;

public enum ApiResponseCode implements ApiResponse {

    RESOURCE_NOT_FOUND("404", "RESOURCE_NOT_FOUND"),

    UNAUTHENTICATION("400", "UNAUTHENTICATION"),

    EMAIL_IS_ALREADY_TAKEN("400", "EMAIL_IS_ALREADY_TAKEN"),

    DO_NOT_HAVE_PERMISSION("400", "DO_NOT_HAVE_PERMISSION"),

    INTERNAL_SERVER_ERROR("500","INTERNAL_SERVER_ERROR"),

    USER_NOT_FOUND("400", "USER_NOT_FOUND"),

    COURSE_IS_NOT_EXISTS("400","COURSE_IS_NOT_EXISTS"),

    SECTION_IS_NOT_EXISTS("400", "SECTION_IS_NOT_EXISTS"),

    LECTURE_IS_NOT_EXISTS("400", "LECTURE_IS_NOT_EXISTS"),

    CONTENT_IS_NOT_EXISTS("400", "CONTENT_IS_NOT_EXISTS"),

    PASSWORD_INCORRECT("400", "PASSWORD_INCORRECT"),

    EMAIL_USED("400", "EMAIL_USED"),

    INVALID_PASSWORD("400", "INVALID_PASSWORD"),

    SUCCESS("200", "SUCCESS"),

    ENTITY_NOT_FOUND("400", "ENTITY_NOT_FOUND"),

    BAD_REQUEST("400", "BAD_REQUEST");

    private String code;
    private String error;

    ApiResponseCode(String code, String error) {
        this.code = code;
        this.error= error;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

}
