package com.pinterestclonebackend.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private Long postId;

    private String postTitle;

    private Integer categoryId;

    private String postDescription;

    private String postImageUrl;

}
