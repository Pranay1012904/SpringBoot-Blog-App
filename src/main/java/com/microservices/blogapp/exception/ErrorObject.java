package com.microservices.blogapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorObject {

    private boolean success;
    private List<ErrorDetails> errorDetails;

}
