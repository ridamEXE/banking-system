package com.bank.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking.dto.LoginRequest;
import com.bank.banking.dto.LoginResponse;
import com.bank.banking.service.AuthService;

@RestController // Marks this as a controller for RESTful services
@RequestMapping("/api/auth") // All requests to this controller start with /api/auth
@CrossOrigin(origins = "http://localhost:4200") // Allows requests from your Angular frontend
public class AuthController {

    @Autowired // Injects the AuthService dependency
    private AuthService authService;

    @PostMapping("/login") // Maps HTTP POST requests to /api/auth/login to this method
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            // Returns a 401 Unauthorized status for failed logins
            return ResponseEntity.status(401).body(response);
        }
    }
}
