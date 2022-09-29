package com.example.musicplayerbackend.controller;

import com.example.musicplayerbackend.model.AuthRequest;
import com.example.musicplayerbackend.repository.UserRepository;
import com.example.musicplayerbackend.service.authservice.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginHandler(@RequestBody @Valid AuthRequest request) {
        return authService.login(request);
    }
}
