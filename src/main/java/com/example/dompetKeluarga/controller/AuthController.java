package com.example.dompetKeluarga.controller;

// controller/AuthController.java
import com.example.dompetKeluarga.dto.LoginRequest;
import com.example.dompetKeluarga.dto.LoginResponse;
import com.example.dompetKeluarga.dto.RegisterRequest;
import com.example.dompetKeluarga.dto.UserDto;
import com.example.dompetKeluarga.execption.GlobalExceptionHandler;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            UserDto userDto = authService.register(request);
            return ResponseEntity.ok(
                    GlobalExceptionHandler.handleSuccess(userDto, "Registration successful")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    GlobalExceptionHandler.handleError(e.getMessage())
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse loginResponse = authService.login(request);
            return ResponseEntity.ok().body(GlobalExceptionHandler.handleSuccess(loginResponse, "Login successful"));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    GlobalExceptionHandler.handleError(e.getMessage())
            );
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // For stateless JWT, just return success.
        return ResponseEntity.ok("Logged out successfully");
    }
}
