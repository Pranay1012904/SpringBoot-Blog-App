package com.microservices.blogapp.service;

import com.microservices.blogapp.entity.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    public Post createPost(Post post);
    public List<Post> getAllPost();
}
