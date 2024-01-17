package com.pinterestclonebackend.demo.dto.mapper;

import com.pinterestclonebackend.demo.dto.request.PostRequestDTO;
import com.pinterestclonebackend.demo.dto.response.PostResponseDTO;
import com.pinterestclonebackend.demo.dto.response.PostResponseForAdminDTO;
import com.pinterestclonebackend.demo.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface PostMapper extends EntityMapper<PostRequestDTO, Post> {
    Post toEntity(PostRequestDTO dto);

    PostResponseDTO toDTO(Post entity);

    List<PostResponseDTO> toDtoListPost(List<Post> entity);

    List<PostResponseForAdminDTO> toDtoListPostForAdmin(List<Post> entity);
}
