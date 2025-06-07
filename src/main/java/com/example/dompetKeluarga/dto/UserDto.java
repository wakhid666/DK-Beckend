package com.example.dompetKeluarga.dto;

import com.example.dompetKeluarga.model.Role;
import lombok.*;


@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private Role role;
}