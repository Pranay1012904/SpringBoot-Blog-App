package com.microservices.blogapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 150)
    @NotEmpty
    private String title;

    @Size(min = 5, max = 300)
    @NotEmpty
    private String description;

    @Size(min = 5, max = 500)
    @NotEmpty
    private String content;
}
