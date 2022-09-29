package com.example.musicplayerbackend.controller;

import com.example.musicplayerbackend.model.User;
import com.example.musicplayerbackend.service.authservice.AuthService;
import com.example.musicplayerbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

   @Autowired
   private AuthService authService;


    @PostMapping("/adduser")
    public ResponseEntity<?> addUserHandler(@RequestBody User user) {
        System.out.println(user);
        user.setPassword(authService.hashPassword(user));
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsersHandler() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserByIdHandler(@PathVariable("userId") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<?> getUserByNameHandler(@PathVariable("userName") String name) {
        return userService.getUserByName(name);
    }

}
