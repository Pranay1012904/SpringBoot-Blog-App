package com.microservices.blogapp.mapstruct;

import com.microservices.blogapp.dto.PostDto;
import com.microservices.blogapp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,componentModel ="spring", uses = PostDto.class)
public interface PostEntityToDTO {

    @Mapping(source="title", target="title")
    @Mapping(source="description", target="description")
    @Mapping(source="content", target="content")
    PostDto postEntityToDTO(Post post);

}
