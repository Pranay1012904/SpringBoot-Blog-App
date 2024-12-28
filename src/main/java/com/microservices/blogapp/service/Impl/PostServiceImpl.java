package com.microservices.blogapp.service.Impl;

import com.microservices.blogapp.entity.Post;
import com.microservices.blogapp.repository.PostRepository;
import com.microservices.blogapp.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private final PostRepository postRepository;

    Post savedPost;

    @Override
    public Post createPost(Post post) {

        try {
           savedPost = postRepository.save(post);
      } catch (Exception e) {
          log.error(String.format("ERROR OCCURES IN CLASS :: %s ERROR:: %s",this.getClass().getSimpleName(),e.getMessage()));
      }
        return savedPost;
    }
}
