package com.microservices.blogapp.mapstruct;

import com.microservices.blogapp.dto.PostDto;
import com.microservices.blogapp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,componentModel ="spring")
public interface PostDTOToEntity {

    @Mapping(source="title", target="title")
    @Mapping(source="description", target="description")
    @Mapping(source="content", target="content")
    Post postDTOToEntity(PostDto post);
}
