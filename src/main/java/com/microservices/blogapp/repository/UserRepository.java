package com.microservices.blogapp.repository;

import com.microservices.blogapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String username);
    Optional<Users> findByEmail(String Email);
    Optional<Users> findByEmailOrUserName(String fieldValue1, String fieldValue2);//standard and takes 2 arguments
    Boolean existsByuserName(String username);//standard naming convention for boolean
    Boolean existsByEmail(String email);//standard naming convention for boolean
}
