package com.microservices.blogapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", status.value());

        // Get all errors
        List<String> exceptionalErrors
                = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField().concat(" "+x.getDefaultMessage()))
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(objectBody, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public static ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                              WebRequest req){
        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false),
                "RESOURCE_NOT_FOUND"
        );
        List<ErrorDetails> errList=new ArrayList<>();
        errList.add(errorDetails);
        ErrorObject errorObject=new ErrorObject(
                false,
                errList
        );
        return ResponseEntity.ok().header("Request_Type","Bad_RES_ID").body(errorObject);
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<ErrorObject> handleGlobalException(Exception ex,
                                                                              WebRequest req){
        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false),
                "SERVER_ERROR"
        );
        List<ErrorDetails> errList=new ArrayList<>();
        errList.add(errorDetails);
        ErrorObject errorObject=new ErrorObject(
                false,
                errList
        );
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
