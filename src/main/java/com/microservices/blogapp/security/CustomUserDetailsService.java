package com.microservices.blogapp.security;

import com.microservices.blogapp.entity.Users;
import com.microservices.blogapp.exception.ResourceNotFoundException;
import com.microservices.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       Users fetchedUser = userRepository.findByEmailOrUserName(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()->new ResourceNotFoundException(
                        "USER",
                        "UsernameOrEmail",
                        usernameOrEmail
                ));
       //
       Set<GrantedAuthority> authorities= fetchedUser.getRoles()
               .stream()
               .map(role-> new SimpleGrantedAuthority(role.getName()))
               .collect(Collectors.toSet());
        return new User(fetchedUser.getEmail(), fetchedUser.getPassword(), authorities);
    }
}
