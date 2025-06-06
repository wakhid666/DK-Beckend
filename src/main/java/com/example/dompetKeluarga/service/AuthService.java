package com.example.dompetKeluarga.service;

// service/AuthService.java
import com.example.dompetKeluarga.dto.LoginRequest;
import com.example.dompetKeluarga.dto.LoginResponse;
import com.example.dompetKeluarga.dto.RegisterRequest;
import com.example.dompetKeluarga.dto.UserDto;
import com.example.dompetKeluarga.model.Role;
import com.example.dompetKeluarga.model.User;
import com.example.dompetKeluarga.repository.UserRepository;
import com.example.dompetKeluarga.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDto register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);

        return UserDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
