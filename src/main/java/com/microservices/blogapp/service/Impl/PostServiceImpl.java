package com.microservices.blogapp.service.Impl;

import com.microservices.blogapp.dto.PageablePostResponse;
import com.microservices.blogapp.dto.PostDto;
import com.microservices.blogapp.entity.Post;
import com.microservices.blogapp.exception.ResourceNotFoundException;
import com.microservices.blogapp.mapstruct.PostEntityToDTO;
import com.microservices.blogapp.repository.PostRepository;
import com.microservices.blogapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    public PostServiceImpl(PostRepository postRepository, PostEntityToDTO postEntityToDTO) {
        this.postRepository = postRepository;
        this.postEntityToDTO = postEntityToDTO;
    }

    private final PostRepository postRepository;
    private final PostEntityToDTO postEntityToDTO;

    Post savedPost;
    List<Post> postList;
    Page<Post> postPage;
    PageablePostResponse ppr;

    @Override
    public Post createPost(Post post) {

        try {
           savedPost = postRepository.save(post);
      } catch (Exception e) {
          log.error(String.format("ERROR OCCURES IN CLASS :: %s ERROR:: %s",this.getClass().getSimpleName(),e.getMessage()));
      }
        return savedPost;
    }

    @Override
    public PageablePostResponse getAllPost(int pageSize, int pageNo) {
        try {
            Pageable pageable= PageRequest.of(pageNo,pageSize);
            postPage = postRepository.findAll(pageable);
            postList = postPage.getContent();
           List<PostDto> dtoList= postList.stream().map(postEntityToDTO::postEntityToDTO).toList();
             ppr=new PageablePostResponse(
                    dtoList,
                    postPage.getNumber(),
                    postPage.getSize(),
                    postPage.getTotalElements(),
                    postPage.getTotalPages()
            );

        }catch(Exception e){
            log.error(String.format("ERROR OCCURES IN CLASS :: %s ERROR:: %s",this.getClass().getSimpleName(),e.getMessage()));
        }
        return ppr;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                    "POST",
                    "ID",
                    String.valueOf(id)
        ));
    }

    @Override
    public Post updatePostById(Post post, Long id) {
          Post fetchedPost  =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(
                    "POST",
                    "ID",
                  String.valueOf(id)
            ));
          fetchedPost.setTitle(post.getTitle());
          fetchedPost.setContent(post.getContent());
          fetchedPost.setDescription(post.getDescription());
        try {
            savedPost = postRepository.save(fetchedPost);
        } catch (Exception e) {
            log.error(String.format("ERROR OCCURES IN CLASS :: %s ERROR:: %s",this.getClass().getSimpleName(),e.getMessage()));
        }
        return savedPost;
    }
}
