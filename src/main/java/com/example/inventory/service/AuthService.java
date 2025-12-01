package com.example.inventory.service;

import com.example.inventory.config.JwtTokenUtil;
import com.example.inventory.model.SystemAccount;
import com.example.inventory.payload.AuthRequest;
import com.example.inventory.payload.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.inventory.payload.ErrorResponse;
import com.example.inventory.repository.SystemAccountRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private SystemAccountRepository accountRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(AuthRequest req) {
        Optional<SystemAccount> opt = accountRepository.findByEmail(req.getEmail());
        if (opt.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("PR40101", "Authentication error"));
        }

        SystemAccount acc = opt.get();

        // So sánh password hash thay vì plaintext
        if (!passwordEncoder.matches(req.getPassword(), acc.getPassword())) {
            return ResponseEntity.status(401).body(new ErrorResponse("PR40101", "Authentication error"));
        }

        if (acc.getIsActive() == null || !acc.getIsActive()) {
            return ResponseEntity.status(403).body(new ErrorResponse("PR40301", "Not authorized"));
        }

        String roleStr = switch (acc.getRole()) {
            case 1 -> "admin";
            case 2 -> "manager";
            case 3 -> "analyst";
            default -> null;
        };

        if (roleStr == null) {
            return ResponseEntity.status(403).body(new ErrorResponse("PR40301", "Not authorized"));
        }

        String token = jwtTokenUtil.generateToken(acc.getEmail(), roleStr);
        return ResponseEntity.ok(new AuthResponse(token, roleStr));
    }
}
