package com.example.dompetKeluarga.execption;

import com.example.dompetKeluarga.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MassageException.class)
    public ResponseEntity<ApiResponse<Object>> handleError(MassageException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<ApiResponse<T>> handleError(String message) {
        ApiResponse<T> response = new ApiResponse<>(
                "error",
                message,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<ApiResponse<T>> handleSuccess(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>(
                "success",
                message,
                data
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
