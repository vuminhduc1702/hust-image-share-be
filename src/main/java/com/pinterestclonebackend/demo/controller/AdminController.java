package com.pinterestclonebackend.demo.controller;

import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.dto.projection.PostProjection;
import com.pinterestclonebackend.demo.dto.response.PostResponseForAdminDTO;
import com.pinterestclonebackend.demo.entity.Post;
import com.pinterestclonebackend.demo.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "A. Admin", description = "Quản lý các post")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/post")
public class AdminController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseForAdminDTO>> getAllPostForAdmin() {
        return ResponseEntity.ok(postService.getPostForAdmin());
    }

    @GetMapping("/{postId}/publish")
    public ResponseEntity<CommonResponseDTO> publishPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.publishPost(postId));
    }

    @GetMapping("/{postId}/unpublish")
    public ResponseEntity<CommonResponseDTO> unpublishPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.unpublishPost(postId));
    }
}
