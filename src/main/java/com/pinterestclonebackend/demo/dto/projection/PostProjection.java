package com.pinterestclonebackend.demo.dto.projection;

public interface PostProjection {
    Long getPostId();

    String getPostTitle();

    String getPostDescription();

    String getPostImageUrl();

    Integer getCategoryId();

    String getCategoryTitle();
}
