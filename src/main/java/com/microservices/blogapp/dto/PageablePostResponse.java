package com.microservices.blogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageablePostResponse {
    private List<PostDto> postDtoList;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    int totalPages;
}
