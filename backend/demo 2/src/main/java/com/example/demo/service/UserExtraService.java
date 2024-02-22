package com.example.demo.service;

import com.example.demo.model.UserExtra;
import com.example.demo.repository.UserExtraRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExtraService {

    @Autowired
    private UserExtraRepository userExtraRepository;

    // Create a new user extra profile
    public UserExtra createUserExtraProfile(UserExtra userExtra) {
        return userExtraRepository.save(userExtra);
    }

    // Get user extra profile by user ID
    public Optional<UserExtra> getUserExtraProfileByUserId(Long userId) {
        return userExtraRepository.findById(userId);
    }

// Update user extra profile
// public UserExtra updateUserExtraProfile(Long userId, UserExtra userExtra) {
//     Optional<UserExtra> existingProfile = userExtraRepository.findById(userId);
//     if (existingProfile.isPresent()) { // Check if the Optional is present
//         UserExtra userProfileToUpdate = existingProfile.get(); // Extract the UserExtra object
//         userProfileToUpdate.setId(userExtra.getId()); // Set the ID
//         return userExtraRepository.save(userProfileToUpdate); // Save the updated profile
//     }
//     return null; // Or throw an exception if the user extra profile doesn't exist
// }

// Update user extra profile
public UserExtra updateUserExtraProfile(Long userId, UserExtra userExtra) {
    Optional<UserExtra> existingProfile = userExtraRepository.findById(userId);
    if (existingProfile.isPresent()) {
        UserExtra userProfileToUpdate = existingProfile.get();
        // Update other fields except the ID
        userProfileToUpdate.setCourse(userExtra.getCourse());
        userProfileToUpdate.setHobbies(userExtra.getHobbies());
        userProfileToUpdate.setSocieties(userExtra.getSocieties());
        userProfileToUpdate.setSports(userExtra.getSports());
        userProfileToUpdate.setEthnicity(userExtra.getEthnicity());
        return userExtraRepository.save(userProfileToUpdate);
    }
    return null; // Or throw an exception if the user extra profile doesn't exist
}

  // Delete user extra profile
public void deleteUserExtraProfile(Long userId) {
    Optional<UserExtra> existingProfile = userExtraRepository.findById(userId);
    existingProfile.ifPresent(userExtraRepository::delete); // Delete if present
}
    }

