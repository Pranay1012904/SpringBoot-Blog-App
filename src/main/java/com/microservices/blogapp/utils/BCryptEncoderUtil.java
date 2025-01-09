package com.microservices.blogapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncoderUtil {

    public static void main(String[] args){
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("pranay"));
        System.out.println(encoder.encode("admin123"));
    }
}
