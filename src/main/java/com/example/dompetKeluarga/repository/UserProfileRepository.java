package com.example.dompetKeluarga.repository;

import com.example.dompetKeluarga.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}

