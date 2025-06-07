package com.example.dompetKeluarga.controller;

// controller/HomeController.java

import com.example.dompetKeluarga.execption.GlobalExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping("/test")
    public ResponseEntity<?> home() {
        try {
            return ResponseEntity.ok("Hello from secured endpoint");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(GlobalExceptionHandler.handleError(e.getMessage()));
        }

    }
}
