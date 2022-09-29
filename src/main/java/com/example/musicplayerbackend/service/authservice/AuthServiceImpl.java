package com.example.musicplayerbackend.service.authservice;

import com.example.musicplayerbackend.model.AuthRequest;
import com.example.musicplayerbackend.model.User;
import com.example.musicplayerbackend.repository.UserRepository;
import com.example.musicplayerbackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @Override
    public String hashPassword(User user) {
        return BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    }

    @Override
    public Boolean checkPassword(User user, String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }

    @Override
    public ResponseEntity<?> login(AuthRequest request) {
        try {
            User user = userRepository.getByEmail(request.getEmail());

            if (checkPassword(user, request.getPassword())) {
                request.setPassword(null);
                return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.generateAccessToken(user));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}




