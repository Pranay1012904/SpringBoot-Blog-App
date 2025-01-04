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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDto>> getAllPost(){
       List<Post> postList= postService.getAllPost();
       List<PostDto> postDtos=new ArrayList<>();
       if(postList.isEmpty()){
           return new ResponseEntity<>(postDtos,HttpStatus.NO_CONTENT);
       }
      postDtos= postList.stream().map(post -> postEntityToDTO.postEntityToDTO(post)).toList();
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id){
           Post fetchedPost = postService.findById(id);
        return new ResponseEntity<>(postEntityToDTO.postEntityToDTO(fetchedPost),HttpStatus.OK);
    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,
                                                  @PathVariable("id") Long id){
          Post updatedPost = postService.updatePostById(postDTOToEntity.postDTOToEntity(postDto),id);
        return new ResponseEntity<>(postEntityToDTO.postEntityToDTO(updatedPost),HttpStatus.OK);
    }
}
