package com.microservices.blogapp.service.Impl;

import com.microservices.blogapp.dto.LoginDto;
import com.microservices.blogapp.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())); //it returns Authentication object
        //then we have to save authentication object in security context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User Login Successful!";
    }
}
