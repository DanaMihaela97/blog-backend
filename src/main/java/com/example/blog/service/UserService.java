package com.example.blog.service;

import com.example.blog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User findById(Long id);

}
