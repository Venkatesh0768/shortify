package org.shortify.shortifybackend.controller;

import lombok.RequiredArgsConstructor;
import org.shortify.shortifybackend.dto.ApiResponse;
import org.shortify.shortifybackend.dto.LoginRequest;
import org.shortify.shortifybackend.dto.RegisterRequest;
import org.shortify.shortifybackend.security.jwt.JwtAuthenticationResponse;
import org.shortify.shortifybackend.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        ApiResponse response = authService.registerUser(request);
        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        JwtAuthenticationResponse response = authService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }


}
