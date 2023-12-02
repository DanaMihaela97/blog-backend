package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/blog")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;

        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<User> findUser(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }

}
