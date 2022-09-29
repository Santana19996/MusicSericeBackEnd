package com.example.musicplayerbackend.service.user;

import com.example.musicplayerbackend.model.User;
import com.example.musicplayerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<?> addUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<User> getUserById(int id) {
        return ResponseEntity.ok(userRepository.getById(id));
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    public ResponseEntity<?> getUserByName(String username) {
        return ResponseEntity.ok(userRepository.findUserByUsername(username));
    }

}

