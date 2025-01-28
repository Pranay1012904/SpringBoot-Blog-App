package com.microservices.blogapp.controller;

import com.microservices.blogapp.dto.LoginDto;
import com.microservices.blogapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/api")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    //build Login API
    @PostMapping(value={"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
                   String res = authService.login(loginDto);
                   return ResponseEntity.ok(res);
    }
}
