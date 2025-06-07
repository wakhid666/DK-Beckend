package com.example.dompetKeluarga.controller;

import com.example.dompetKeluarga.model.UserProfile;
import com.example.dompetKeluarga.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/save")
    public UserProfile saveUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.saveUserProfile(userProfile);
    }
}

