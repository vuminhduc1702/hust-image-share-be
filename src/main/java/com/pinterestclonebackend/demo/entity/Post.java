package com.pinterestclonebackend.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postTitle;

    private String postDescription;

    private Integer categoryId;

    private String postImageUrl;

    private Boolean isPublished;

    private Long userId;

    @PrePersist
    public void setDefaultValue() {
        if (this.isPublished == null) {
            this.setIsPublished(false);
        }
    }
}
