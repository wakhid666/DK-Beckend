package com.example.dompetKeluarga.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class LoginResponse {
    private String token;
}
