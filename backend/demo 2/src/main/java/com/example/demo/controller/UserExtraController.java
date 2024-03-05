package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserExtraDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserExtra;
import com.example.demo.service.UserExtraService;
import com.example.demo.service.UserService;
//
//import com.example.demo.dto.UserPostDTO;

@RestController
public class UserExtraController {

    @Autowired
    private UserExtraService userExtraService;

    @Autowired
    private UserService userService; // Autowire the UserService

    @GetMapping("/user-extra")
    public List<UserExtra> getUserExtras() {
        return userExtraService.getUserExtra();
    }

    @PostMapping("/user-extra")
    public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {

        UserExtra savedUserExtra = userExtraService.createUserExtraProfile(userExtra);
        return new ResponseEntity<>(savedUserExtra, HttpStatus.CREATED);
    }

    // @PostMapping("/user-extra")
    // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
    //     try {
    //         UserExtra userExtra = new UserExtra();
    //         userExtra.setCourse(userExtraDTO.getCourse());
    //         userExtra.setHobbies(userExtraDTO.getHobbies());
    //         userExtra.setSocieties(userExtraDTO.getSocieties());
    //         userExtra.setSports(userExtraDTO.getSports());
    //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
    //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
    //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }


    // @PostMapping("/user-extra")
    // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
    //     try {
    //         // Create an instance of UserExtraDTO
    //         UserExtraDTO userExtraDTOInstance = new UserExtraDTO();
    
    //         // Now you can call getEmail() on the instance
    //         User user = userService.findByEmail(userExtraDTOInstance.getEmail());
            
    //         if (user == null) {
    //             // Handle user not found error
    //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //         }
            
    //         UserExtra userExtra = new UserExtra();
    //         userExtra.setUser(user);
    //         userExtra.setCourse(userExtraDTO.getCourse());
    //         userExtra.setHobbies(userExtraDTO.getHobbies());
    //         userExtra.setSocieties(userExtraDTO.getSocieties());
    //         userExtra.setSports(userExtraDTO.getSports());
    //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
    //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
    //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         // Handle exception
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    
    // @PostMapping("/user-extra")
    // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
    //     try {
    //         User user = userService.findByEmail(UserExtraDTO.getEmail()); // Fetch user by email
    //         if (user == null) {
    //             // Handle user not found error
    //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //         }
            
    //         UserExtra userExtra = new UserExtra();
    //         userExtra.setUser(user);
    //         userExtra.setCourse(userExtraDTO.getCourse());
    //         userExtra.setHobbies(userExtraDTO.getHobbies());
    //         userExtra.setSocieties(userExtraDTO.getSocieties());
    //         userExtra.setSports(userExtraDTO.getSports());
    //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
    //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
    //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         // Handle exception
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("/user-extra/{userId}")
    public ResponseEntity<UserExtra> getUserExtraProfileByUserId(@PathVariable Long userId) {
        Optional<UserExtra> userExtraOptional = userExtraService.getUserExtraProfileByUserId(userId);
        return userExtraOptional.map(userExtra -> new ResponseEntity<>(userExtra, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user-extra/update/{userId}")
    public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable Long userId, @RequestBody UserExtra userExtra) {
        UserExtra updatedProfile = userExtraService.updateUserExtraProfile(userId, userExtra);
        return updatedProfile != null ?
                new ResponseEntity<>(updatedProfile, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user-extra/delete/{userId}")
    public ResponseEntity<Void> deleteUserExtraProfile(@PathVariable Long userId) {
        userExtraService.deleteUserExtraProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}












    // @PostMapping("/user-extra")
    // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
    //     try {
    //         UserExtra userExtra = new UserExtra();
    //         userExtra.setCourse(userExtraDTO.getCourse());
    //         userExtra.setHobbies(userExtraDTO.getHobbies());
    //         userExtra.setSocieties(userExtraDTO.getSocieties());
    //         userExtra.setSports(userExtraDTO.getSports());
    //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
    //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
    //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    // @PostMapping("/user-extra")
    // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
    //     try {
    //         // Assuming you have userId available in your UserExtraDTO object
    //         Long userId = userExtraDTO.getUserId();
    
    //         UserExtra userExtra = new UserExtra();
    //         userExtra.setCourse(userExtraDTO.getCourse());
    //         userExtra.setHobbies(userExtraDTO.getHobbies());
    //         userExtra.setSocieties(userExtraDTO.getSocieties());
    //         userExtra.setSports(userExtraDTO.getSports());
    //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
    //         // Pass both userExtra and userId to createUserExtraProfile method
    //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra, userId);
    //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    
   

    // @PostMapping("/user-extra")
    // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
    //     try {
    //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
    //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

// package com.example.demo.controller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.example.demo.model.UserExtra;
// import com.example.demo.service.UserExtraService;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// @RestController
// //@RequestMapping("/user-extra")
// public class UserExtraController {
//     private static final Logger logger = LoggerFactory.getLogger(UserExtraController.class);
//     @Autowired
//     private UserExtraService userExtraService;
//     // Create a new user extra profile
//     // @PostMapping("/create")
//     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
//     //     logger.info("Received request to create user extra profile: {}", userExtra);
//     //     UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
//     //     logger.info("User extra profile created: {}", createdProfile);
//     //     return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
//     // }
//     @PostMapping("/user-extra")
//     public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
//         try {
//             logger.info("Received request to create user extra profile: {}", userExtra);
//             UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
//             logger.info("User extra profile created: {}", createdProfile);
//             return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
//         } catch (Exception e) {
//             logger.error("Error creating user extra profile", e);
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }
    

//    // Get user extra profile by user ID
// @GetMapping("/{userId}")
// public ResponseEntity<UserExtra> getUserExtraProfileByUserId(@PathVariable Long userId) {
//     Optional<UserExtra> userExtraOptional = userExtraService.getUserExtraProfileByUserId(userId);
//     if (userExtraOptional.isPresent()) {
//         return new ResponseEntity<>(userExtraOptional.get(), HttpStatus.OK);
//     } else {
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//     }
// }
//     // Update user extra profile
//     @PutMapping("/update/{userId}")
//     public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable Long userId, @RequestBody UserExtra userExtra) {
//         UserExtra updatedProfile = userExtraService.updateUserExtraProfile(userId, userExtra);
//         if (updatedProfile != null) {
//             return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
//     // Delete user extra profile
//     @DeleteMapping("/delete/{userId}")
//     public ResponseEntity<Void> deleteUserExtraProfile(@PathVariable Long userId) {
//         userExtraService.deleteUserExtraProfile(userId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
// }
