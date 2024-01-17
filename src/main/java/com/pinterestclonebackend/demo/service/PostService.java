package com.pinterestclonebackend.demo.service;

import com.pinterestclonebackend.demo.constants.ApiResponseCode;
import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.dto.mapper.PostMapper;
import com.pinterestclonebackend.demo.dto.projection.PostProjection;
import com.pinterestclonebackend.demo.dto.request.PostRequestDTO;
import com.pinterestclonebackend.demo.dto.response.PostResponseDTO;
import com.pinterestclonebackend.demo.dto.response.PostResponseForAdminDTO;
import com.pinterestclonebackend.demo.entity.AwsS3Bucket;
import com.pinterestclonebackend.demo.entity.Category;
import com.pinterestclonebackend.demo.entity.Post;
import com.pinterestclonebackend.demo.exception.BusinessException;
import com.pinterestclonebackend.demo.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final PostMapper postMapper;

    private final AwsS3Service awsS3Service;

    public CommonResponseDTO createNewPost(String postTitle, String categoryId, String postDescription, MultipartFile multipartFile, Long userId) {
        Post post = Post.builder().postTitle(postTitle).categoryId(Integer.parseInt(categoryId)).postDescription(postDescription).build();
        String imageUrl = awsS3Service.uploadFile(multipartFile);
        post.setUserId(userId);
        post.setPostImageUrl(imageUrl);
        postRepository.save(post);
        return new CommonResponseDTO("200", "OK");
    }

    public List<PostResponseDTO> getPostByCategoryForUser(Integer categoryId) {
        return postMapper.toDtoListPost(postRepository.findByCategoryId(categoryId));
    }

    public List<PostResponseForAdminDTO> getPostForAdmin() {
//        return postMapper.toDtoListPostForAdmin(postRepository.findAllForAdmin());
        return postMapper.toDtoListPostForAdmin(postRepository.findAll());
    }

    public List<PostResponseDTO> searchPost(String keyword) {
        return postMapper.toDtoListPost(postRepository.searchPost(keyword));
    }

    public CommonResponseDTO publishPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BusinessException(ApiResponseCode.CONTENT_IS_NOT_EXISTS));
        post.setIsPublished(true);
        postRepository.save(post);
        return new CommonResponseDTO("200", "Ok");
    }

    public CommonResponseDTO unpublishPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new BusinessException(ApiResponseCode.CONTENT_IS_NOT_EXISTS));
        post.setIsPublished(false);
        postRepository.save(post);
        return new CommonResponseDTO("200", "Ok");
    }
}
