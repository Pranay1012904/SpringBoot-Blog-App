package com.microservices.blogapp.controller;

import com.microservices.blogapp.dto.PostDto;
import com.microservices.blogapp.entity.Post;
import com.microservices.blogapp.mapstruct.PostDTOToEntity;
import com.microservices.blogapp.mapstruct.PostEntityToDTO;
import com.microservices.blogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/post/api")
@AllArgsConstructor
public class PostController {

    private PostService postService;
    private PostDTOToEntity postDTOToEntity;
    private PostEntityToDTO postEntityToDTO;

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        Post savedPost = postService.createPost(postDTOToEntity.postDTOToEntity(postDto));
    return new ResponseEntity<>(postEntityToDTO.postEntityToDTO(savedPost), HttpStatus.OK);
    }
}
