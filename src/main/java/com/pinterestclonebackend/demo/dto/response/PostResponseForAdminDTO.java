package com.pinterestclonebackend.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseForAdminDTO {
    private Long postId;

    private String postTitle;

    private Integer categoryId;

    private String categoryTitle;

    private String postDescription;

    private String postImageUrl;

    private Boolean isPublished;
}
