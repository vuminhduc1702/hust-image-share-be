package com.pinterestclonebackend.demo.controller;

import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.entity.Category;
import com.pinterestclonebackend.demo.service.PublicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "O.Overall", description = "Các thông tin public")
@RestController
@AllArgsConstructor
@RequestMapping("/api/public")
@CrossOrigin
public class OverallController {

    private final PublicService publicService;

    @Operation(summary = "Get all category item", description = "Lấy danh sách category")
    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(publicService.findAllCategory());
    }

    @Operation(summary = "Get category item by categoryId", description = "Lấy category có id")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok(publicService.findCategoryById(categoryId));
    }


}
