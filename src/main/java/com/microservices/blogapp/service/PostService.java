package com.microservices.blogapp.service;

import com.microservices.blogapp.entity.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    public Post createPost(Post post);
}
