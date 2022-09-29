package com.example.musicplayerbackend.service.authservice;

import com.example.musicplayerbackend.model.AuthRequest;
import com.example.musicplayerbackend.model.User;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    String hashPassword(User user);
    Boolean checkPassword(User user, String password);
    ResponseEntity<?> login(AuthRequest request);
}
