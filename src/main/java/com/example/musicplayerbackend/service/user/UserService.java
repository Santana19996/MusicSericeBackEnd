package com.example.musicplayerbackend.service.user;

import com.example.musicplayerbackend.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> addUser(User user);
    ResponseEntity<User> getUserById(int id);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> getUserByName(String name);
}
