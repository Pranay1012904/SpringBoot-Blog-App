package com.microservices.blogapp.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    //Authentication Manager Configuration
    //AuthenticationConfiguration Provides AuthenticationManager Instance
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize-> authorize.requestMatchers(HttpMethod.GET,"/post/api/**")
                        .permitAll().requestMatchers("auth/api/**").permitAll().anyRequest().authenticated())//here we are giving GET request permits to all users
                       .httpBasic(Customizer.withDefaults());
     return http.build();
    }

   /* @Bean  NOT REQUIRED IN DB AUTHENTICATION
    public UserDetailsService userDetailsService(){
        UserDetails pranay= User.builder()
                .username("pranay").password(passwordEncoder().encode("pranay"))
                .roles("USER")
                .build();

        UserDetails admin=User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(pranay,admin);
    }*/
}
