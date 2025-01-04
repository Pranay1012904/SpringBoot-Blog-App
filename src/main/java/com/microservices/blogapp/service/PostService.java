package com.microservices.blogapp.service;

import com.microservices.blogapp.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

     Post createPost(Post post);
     List<Post> getAllPost();
     Post findById(Long id);
    Post updatePostById(Post post,Long id);
}
