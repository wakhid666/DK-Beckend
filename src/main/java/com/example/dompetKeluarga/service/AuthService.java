package com.example.dompetKeluarga.service;

import com.example.dompetKeluarga.dto.LoginRequest;
import com.example.dompetKeluarga.dto.LoginResponse;
import com.example.dompetKeluarga.dto.RegisterRequest;
import com.example.dompetKeluarga.dto.UserDto;
import com.example.dompetKeluarga.execption.MassageException;
import com.example.dompetKeluarga.model.Role;
import com.example.dompetKeluarga.model.User;
import com.example.dompetKeluarga.model.UserProfile;
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
    private final UserProfileService userProfileService;

    public UserDto register(RegisterRequest request) {
        if (request.getIsAdmin() == null) {
            throw  new MassageException("Role must not be null");
        }
        if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new MassageException("Username and password must not be null");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new MassageException("Username already exists");
        }

        if (request.getPassword().length() < 8) {
            throw new MassageException("Password must be at least 8 characters");
        }

        if (request.getUsername().length() < 3) {
            throw new MassageException("Username must be at least 3 characters");
        }

        Role role = null;
        if (!request.getRole().isEmpty()){
            if (request.getRole().equalsIgnoreCase("ADMIN")) {
                role = Role.ADMIN;
            } else if (request.getRole().equalsIgnoreCase("USER")) {
                role = Role.USER;
            } else {
                throw new MassageException("Invalid role. Must be either ADMIN or USER");
            }
        }

        var user = User.builder()
                .id(null)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        var savedUser = userRepository.save(user);
        // Simpan user profile setelah user berhasil dibuat
        UserProfile userProfile = UserProfile.builder()
                .fullName(request.getFullName())
                .gender(request.getGender())
                .user(savedUser)
                .build();
        userProfileService.saveUserProfile(userProfile);

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