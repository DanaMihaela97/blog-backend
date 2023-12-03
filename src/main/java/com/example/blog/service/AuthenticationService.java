package com.example.blog.service;

import com.example.blog.dto.AuthenticationRequest;
import com.example.blog.dto.AuthenticationResponse;
import com.example.blog.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse signup(RegisterRequest request);

    AuthenticationResponse signin(AuthenticationRequest request);
}