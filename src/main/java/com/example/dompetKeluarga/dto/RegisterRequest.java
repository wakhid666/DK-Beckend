package com.example.dompetKeluarga.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
    private String fullName;
    private String gender;

    public String getIsAdmin() {
        return role;
    }
}