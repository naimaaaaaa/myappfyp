package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.UserExtra;
import com.example.demo.service.UserExtraService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-extra")
public class UserExtraController {
    private static final Logger logger = LoggerFactory.getLogger(UserExtraController.class);

    @Autowired
    private UserExtraService userExtraService;

    // Create a new user extra profile
    @PostMapping("/create")
    public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
        logger.info("Received request to create user extra profile: {}", userExtra);

        UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
        logger.info("User extra profile created: {}", createdProfile);

        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

   // Get user extra profile by user ID
@GetMapping("/{userId}")
public ResponseEntity<UserExtra> getUserExtraProfileByUserId(@PathVariable Long userId) {
    Optional<UserExtra> userExtraOptional = userExtraService.getUserExtraProfileByUserId(userId);
    if (userExtraOptional.isPresent()) {
        return new ResponseEntity<>(userExtraOptional.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    // Update user extra profile
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable Long userId, @RequestBody UserExtra userExtra) {
        UserExtra updatedProfile = userExtraService.updateUserExtraProfile(userId, userExtra);
        if (updatedProfile != null) {
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete user extra profile
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUserExtraProfile(@PathVariable Long userId) {
        userExtraService.deleteUserExtraProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}





