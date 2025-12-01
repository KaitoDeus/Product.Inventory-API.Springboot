package com.example.inventory.controller;

import com.example.inventory.payload.AuthRequest;
import com.example.inventory.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "User login and token generation")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    @Operation(summary = "Login", description = "Authenticate user and get JWT token")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        return authService.login(req);
    }
}
