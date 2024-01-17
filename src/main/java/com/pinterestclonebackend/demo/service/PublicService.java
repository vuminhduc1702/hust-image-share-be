package com.pinterestclonebackend.demo.service;

import com.pinterestclonebackend.demo.dto.CommonResponseDTO;
import com.pinterestclonebackend.demo.entity.Category;
import com.pinterestclonebackend.demo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicService {
    private final CategoryRepository categoryRepository;

    private final AwsS3Service awsS3Service;

    public Category saveCategory(String categoryTitle, String categoryDescription, MultipartFile categoryImage, MultipartFile categoryBackground) {
        Category category = Category.builder().categoryTitle(categoryTitle).categoryDescription(categoryDescription).build();
        String categoryImageUrl = awsS3Service.uploadFile(categoryImage);
        String categoryBackgroundUrl = awsS3Service.uploadFile(categoryBackground);
        category.setCategoryImageUrl(categoryImageUrl);
        category.setCategoryBackgroundImageUrl(categoryBackgroundUrl);

        return categoryRepository.save(category);
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(String categoryId) {
        return categoryRepository.findById(Integer.parseInt(categoryId)).orElse(null);
    }
}
