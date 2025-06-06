package com.example.dompetKeluarga.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    @Getter
    private final String role;

}
