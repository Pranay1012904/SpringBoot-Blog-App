package com.microservices.blogapp.dto;

import lombok.Data;

@Data
public class PostDto {
    private String title;
    private String description;
    private String content;
}
