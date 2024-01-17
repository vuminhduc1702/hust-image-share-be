package com.pinterestclonebackend.demo.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class PostRequestDTO {

    @Length(max = 60)
    @NotNull
    private String postTitle;

    @NotNull
    @Max(value = 12, message = "Category không tồn tại")
    @Min(value = 1, message = "Category không tồn tại")
    private Integer categoryId;

    private String postDescription;
}
