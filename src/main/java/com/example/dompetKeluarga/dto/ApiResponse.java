package com.example.dompetKeluarga.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    // Getters and setters
    private String status;
    private String message;
    private T data;
    private Object pagination;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.pagination = null;
    }
    public ApiResponse(String status, String message, T data, Object pagination) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.pagination = pagination;
    }

}
