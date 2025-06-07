package com.example.dompetKeluarga.service;

import com.example.dompetKeluarga.model.UserProfile;
import com.example.dompetKeluarga.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}

