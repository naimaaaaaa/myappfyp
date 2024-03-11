// package com.example.demo.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.dto.UserExtraDTO;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.User;
// import com.example.demo.model.UserExtra;
// import com.example.demo.service.UserExtraService;
// import com.example.demo.service.UserService;
// //
// //import com.example.demo.dto.UserPostDTO;

// @RestController
// public class UserExtraController {

//     @Autowired
//     private UserExtraService userExtraService;

//     @Autowired
//     private UserService userService; // Autowire the UserService

   
//     @GetMapping("/user-extra")
//     public List<UserExtra> getUserExtras() {
//         return userExtraService.getUserExtra();
//     }

//     @GetMapping("/user-extra/{userId}")
// public ResponseEntity<Optional<UserExtra>> getUserExtra(@PathVariable Long userId) {
//     Optional<UserExtra> userExtra = userExtraService.getUserExtraProfileByUserId(userId);
//     if (userExtra != null) {
//         return ResponseEntity.ok(userExtra);
//     } else {
//         return ResponseEntity.notFound().build();
//     }
// }

//     // @PostMapping("/user-extra")
//     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
//     //     // Check if the user already has extra information
//     //     Optional<UserExtra> existingExtra = userExtraService.getUserExtraProfileByUserId(userExtra.getUser().getId());
//     //     if (existingExtra.isPresent()) {
//     //         // User already has extra information, return conflict status
//     //         return new ResponseEntity<>(HttpStatus.CONFLICT);
//     //     }

//     //     UserExtra savedUserExtra = userExtraService.createUserExtraProfile(userExtra);
//     //     return new ResponseEntity<>(savedUserExtra, HttpStatus.CREATED);
//     // }
// // POST route for creating a new extra profile
//   // POST route for creating a new extra profile or updating an existing one


// //   @PostMapping("/user-extra")
// // public ResponseEntity<?> createUserExtraProfile(@RequestBody UserExtra userExtra) {
// //     try {
// //         Long userId = userExtra.getUser().getId();

// //         // Check if user exists
// //         Optional<User> user = userService.findById(userId);
// //         if (user.isPresent()) {
// //             // If user exists, check if user already has an extra profile
// //             Optional<UserExtra> existingExtra = userExtraService.getUserExtraProfileByUserId(userId);
// //             if (existingExtra.isPresent()) {
// //                 // If profile exists, return conflict status
// //                 return ResponseEntity.status(HttpStatus.CONFLICT).body("User already has an extra profile");
// //             } else {
// //                 // If no profile exists, create a new one
// //                 UserExtra savedUserExtra = userExtraService.createUserExtraProfile(userExtra);
// //                 return ResponseEntity.status(HttpStatus.CREATED).body(savedUserExtra);
// //             }
// //         } else {
// //             // If user does not exist, return not found status
// //             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
// //         }
// //     } catch (Exception e) {
// //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user extra profile");
// //     }
// // }



// @PutMapping("/user-extra/{userId}")
// public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable Long userId, @RequestBody UserExtra userExtra) {
//     System.out.println("Updating user extra profile for user ID: " + userId); // Log user ID

//     try {
//         // Update the user extra profile
//         UserExtra updatedUserExtra = userExtraService.updateUserExtraProfile(userId, userExtra);
//         return ResponseEntity.ok(updatedUserExtra);
//     } catch (ResourceNotFoundException ex) {
//         // User extra profile not found, return 404 status
//         return ResponseEntity.notFound().build();
//     }
// }

//     @DeleteMapping("/user-extra/{userId}")
//     public ResponseEntity<?> deleteUserExtraProfile(@PathVariable Long userId) {
//         try {
//             // Your logic to delete the user extra profile with the given userId
//             userExtraService.deleteUserExtraProfile(userId);
//             return ResponseEntity.ok().build();
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user extra profile");
//         }
//     }
// }


//  // @GetMapping("/user-extra")
//     // public List<UserExtra> getUserExtras() {
//     //     return userExtraService.getUserExtra();
//     // }


//     // @PostMapping("/user-extra")
//     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {

//     //     UserExtra savedUserExtra = userExtraService.createUserExtraProfile(userExtra);
//     //     return new ResponseEntity<>(savedUserExtra, HttpStatus.CREATED);
//     // }


//  //previous
//     // @DeleteMapping("/delete/{userId}")
//     // // public ResponseEntity<?> deleteUserExtraProfile(@PathVariable Long userId) {
//     //     public ResponseEntity<?> deleteUserExtraProfile(@PathVariable(value = "id") long userId) {
//     //     try {
//     //         userExtraService.deleteUserExtraProfile(userId);
//     //         return ResponseEntity.ok().build();
//     //     } catch (Exception e) {
//     //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user extra profile");
//     //     }
//     // }



//  // @DeleteMapping("/user-extra/{userId}")
//     // public ResponseEntity<?> deleteUserExtraProfile(@PathVariable Long userId) {
//     //     // Check if the user extra profile exists
//     //     Optional<UserExtra> existingExtra = userExtraService.getUserExtraProfileByUserId(userId);
//     //     if (existingExtra.isEmpty()) {
//     //         // User extra profile does not exist, return not found status
//     //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//     //     }

//     //     // Delete the user extra profile
//     //     userExtraService.deleteUserExtraProfile(userId);
//     //     return ResponseEntity.ok().build();
//     // }

//     // @DeleteMapping("/user-extra/delete/{userId}")
//     // public ResponseEntity<Void> deleteUserExtraProfile(@PathVariable Long userId) {
//     //     userExtraService.deleteUserExtraProfile(userId);
//     //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     // }
//     // @GetMapping("/user-extra/{userId}")
//     // public ResponseEntity<UserExtra> getUserExtraProfileByUserId(@PathVariable Long userId) {
//     //     Optional<UserExtra> userExtraOptional = userExtraService.getUserExtraProfileByUserId(userId);
//     //     return userExtraOptional.map(userExtra -> new ResponseEntity<>(userExtra, HttpStatus.OK))
//     //             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     // }

//     // @PutMapping("/user-extra/update/{userId}")
//     // public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable Long userId, @RequestBody UserExtra userExtra) {
//     //     UserExtra updatedProfile = userExtraService.updateUserExtraProfile(userId, userExtra);
//     //     return updatedProfile != null ?
//     //             new ResponseEntity<>(updatedProfile, HttpStatus.OK) :
//     //             new ResponseEntity<>(HttpStatus.NOT_FOUND);
//     // }


//     //previousss
// //     @PutMapping("/updateUserExtra/{userId}")
// //    // public ResponseEntity<UserExtra> updateUserExtraProfile(long userId, @RequestBody UserExtra userExtra) {

// //     public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable(value = "id") long userId, @RequestBody UserExtra userExtra) {
// //         // Delete existing information for the user
// //         userExtraService.deleteUserExtraProfile(userId);
        
// //         // Save the new user extra profile
// //         UserExtra updatedUserExtra = userExtraService.updateUserExtraProfile(userId, userExtra);
// //         return ResponseEntity.ok(updatedUserExtra);
// //     }



//     // @PostMapping("/user-extra")
//     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
//     //     try {
//     //         UserExtra userExtra = new UserExtra();
//     //         userExtra.setCourse(userExtraDTO.getCourse());
//     //         userExtra.setHobbies(userExtraDTO.getHobbies());
//     //         userExtra.setSocieties(userExtraDTO.getSocieties());
//     //         userExtra.setSports(userExtraDTO.getSports());
//     //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
//     //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
//     //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
//     //     } catch (Exception e) {
//     //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//     //     }
//     // }
//     // @PostMapping("/user-extra")
//     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtraDTO userExtraDTO) {
//     //     try {
//     //         // Assuming you have userId available in your UserExtraDTO object
//     //         Long userId = userExtraDTO.getUserId();
    
//     //         UserExtra userExtra = new UserExtra();
//     //         userExtra.setCourse(userExtraDTO.getCourse());
//     //         userExtra.setHobbies(userExtraDTO.getHobbies());
//     //         userExtra.setSocieties(userExtraDTO.getSocieties());
//     //         userExtra.setSports(userExtraDTO.getSports());
//     //         userExtra.setEthnicity(userExtraDTO.getEthnicity());
            
//     //         // Pass both userExtra and userId to createUserExtraProfile method
//     //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra, userId);
//     //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
//     //     } catch (Exception e) {
//     //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//     //     }
//     // }
    
   

//     // @PostMapping("/user-extra")
//     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
//     //     try {
//     //         UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
//     //         return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
//     //     } catch (Exception e) {
//     //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//     //     }
//     // }

// // package com.example.demo.controller;

// // import org.slf4j.Logger;
// // import org.slf4j.LoggerFactory;

// // import com.example.demo.model.UserExtra;
// // import com.example.demo.service.UserExtraService;

// // import java.util.Optional;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;
// // @RestController
// // //@RequestMapping("/user-extra")
// // public class UserExtraController {
// //     private static final Logger logger = LoggerFactory.getLogger(UserExtraController.class);
// //     @Autowired
// //     private UserExtraService userExtraService;
// //     // Create a new user extra profile
// //     // @PostMapping("/create")
// //     // public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
// //     //     logger.info("Received request to create user extra profile: {}", userExtra);
// //     //     UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
// //     //     logger.info("User extra profile created: {}", createdProfile);
// //     //     return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
// //     // }
// //     @PostMapping("/user-extra")
// //     public ResponseEntity<UserExtra> createUserExtraProfile(@RequestBody UserExtra userExtra) {
// //         try {
// //             logger.info("Received request to create user extra profile: {}", userExtra);
// //             UserExtra createdProfile = userExtraService.createUserExtraProfile(userExtra);
// //             logger.info("User extra profile created: {}", createdProfile);
// //             return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
// //         } catch (Exception e) {
// //             logger.error("Error creating user extra profile", e);
// //             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
// //         }
// //     }
    

// //    // Get user extra profile by user ID
// // @GetMapping("/{userId}")
// // public ResponseEntity<UserExtra> getUserExtraProfileByUserId(@PathVariable Long userId) {
// //     Optional<UserExtra> userExtraOptional = userExtraService.getUserExtraProfileByUserId(userId);
// //     if (userExtraOptional.isPresent()) {
// //         return new ResponseEntity<>(userExtraOptional.get(), HttpStatus.OK);
// //     } else {
// //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// //     }
// // }
// //     // Update user extra profile
// //     @PutMapping("/update/{userId}")
// //     public ResponseEntity<UserExtra> updateUserExtraProfile(@PathVariable Long userId, @RequestBody UserExtra userExtra) {
// //         UserExtra updatedProfile = userExtraService.updateUserExtraProfile(userId, userExtra);
// //         if (updatedProfile != null) {
// //             return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
// //         } else {
// //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// //         }
// //     }
// //     // Delete user extra profile
// //     @DeleteMapping("/delete/{userId}")
// //     public ResponseEntity<Void> deleteUserExtraProfile(@PathVariable Long userId) {
// //         userExtraService.deleteUserExtraProfile(userId);
// //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
// //     }
// // }
