package com.wedding.backend.controller;

import com.wedding.backend.dto.JwtResponse;
import com.wedding.backend.dto.LoginRequest;
import com.wedding.backend.dto.RegisterRequest;
import com.wedding.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        authService.register(signUpRequest);
        return ResponseEntity.ok("User registered successfully!");
    }
}
