package com.pinterestclonebackend.demo.controller;

import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.dto.request.PostRequestDTO;
import com.pinterestclonebackend.demo.dto.response.PostResponseDTO;
import com.pinterestclonebackend.demo.service.PostService;
import com.pinterestclonebackend.demo.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "P.Post", description = "Lưu post")
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user/post")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> searchPost(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(postService.searchPost(keyword));
    }

    @Operation(summary = "Get list of posts for user", description = "Lấy các post đã published theo chủ đề")
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<PostResponseDTO>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(postService.getPostByCategoryForUser(categoryId));
    }

    @Operation(summary = "Create a new post", description = "Tạo 1 bài post mới")
    @PostMapping
    public ResponseEntity<CommonResponseDTO> createNewPost(@RequestParam("postTitle") String postTitle, @RequestParam("categoryId") String categoryId, @RequestParam("postDescription") String postDescription, @RequestPart MultipartFile multipartFile) {
        return ResponseEntity.ok(postService.createNewPost(postTitle, categoryId, postDescription, multipartFile, SecurityUtils.getCurrentUserIdLogin().get()));
    }
}
