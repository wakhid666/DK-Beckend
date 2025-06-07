package com.example.dompetKeluarga.service;

import com.example.dompetKeluarga.dto.LoginRequest;
import com.example.dompetKeluarga.dto.LoginResponse;
import com.example.dompetKeluarga.dto.RegisterRequest;
import com.example.dompetKeluarga.dto.UserDto;
import com.example.dompetKeluarga.execption.MassageException;
import com.example.dompetKeluarga.model.Role;
import com.example.dompetKeluarga.model.User;
import com.example.dompetKeluarga.repository.UserRepository;
import com.example.dompetKeluarga.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserDto register(RegisterRequest request) {
        if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new MassageException("Username and password must not be null");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new MassageException("Username already exists");
        }

        var user = User.builder()
                .id(null)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);

        return UserDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new MassageException("Username and password must not be null");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new MassageException("Failed to generate JWT token");
        }

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}