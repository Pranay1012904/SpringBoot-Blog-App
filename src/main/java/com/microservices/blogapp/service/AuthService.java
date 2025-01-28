package com.microservices.blogapp.service;

import com.microservices.blogapp.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
