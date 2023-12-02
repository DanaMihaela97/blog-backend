package com.example.blog.controller;

import com.example.blog.dto.AuthenticationRequest;
import com.example.blog.dto.AuthenticationResponse;
import com.example.blog.dto.RegisterRequest;
import com.example.blog.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }
}
