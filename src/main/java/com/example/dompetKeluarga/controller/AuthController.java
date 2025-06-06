package com.example.dompetKeluarga.controller;

// controller/AuthController.java
import com.example.dompetKeluarga.dto.LoginRequest;
import com.example.dompetKeluarga.dto.LoginResponse;
import com.example.dompetKeluarga.dto.RegisterRequest;
import com.example.dompetKeluarga.dto.UserDto;
import com.example.dompetKeluarga.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
