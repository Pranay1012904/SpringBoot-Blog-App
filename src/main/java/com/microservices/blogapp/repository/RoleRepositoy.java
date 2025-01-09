package com.microservices.blogapp.repository;

import com.microservices.blogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoy extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
