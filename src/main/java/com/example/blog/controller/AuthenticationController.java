package com.example.blog.controller;

import com.example.blog.dto.AuthenticationRequest;
import com.example.blog.dto.AuthenticationResponse;
import com.example.blog.dto.RegisterRequest;
import com.example.blog.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        if(request.getUsername().contains("@gmail"))
            request.setEmail(request.getUsername());
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}
