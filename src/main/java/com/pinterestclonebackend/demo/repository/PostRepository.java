package com.pinterestclonebackend.demo.repository;

import com.pinterestclonebackend.demo.dto.projection.PostProjection;
import com.pinterestclonebackend.demo.dto.response.PostResponseDTO;
import com.pinterestclonebackend.demo.dto.response.PostResponseForAdminDTO;
import com.pinterestclonebackend.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT post from Post post WHERE post.categoryId = :categoryId AND post.isPublished = TRUE")
    List<Post> findByCategoryId(@Param("categoryId") Integer categoryId);

    @Query(value = "SELECT p, c.categoryTitle FROM Post p JOIN Category c ON p.categoryId = c.categoryId")
    List<PostProjection> findAllForAdmin();

    @Query(value = "SELECT post from Post post WHERE post.isPublished = TRUE AND post.postTitle LIKE %?1%")
    List<Post> searchPost(String keyword);
    List<Post> findByUserId(@Param("userId") Long userId);
}
