package com.example.blog.service.impl;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

}
