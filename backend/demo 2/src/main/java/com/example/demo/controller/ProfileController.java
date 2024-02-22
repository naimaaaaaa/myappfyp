// package com.example.demo.controller;

// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;

// @RestController
// @CrossOrigin
// public class ProfileController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/profile/picture/{email}")
//     public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file, @PathVariable String email) {
//         try {
//             userService.updateProfilePicture(file, email);
//             return ResponseEntity.ok("Profile picture uploaded successfully.");
//         } catch (IOException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile picture: " + e.getMessage());
//         }
//     }

//     @GetMapping("/profile/picture/{email}")
//     public ResponseEntity<byte[]> getProfilePicture(@PathVariable String email) {
//         try {
//             byte[] imageBytes = userService.getProfilePicture(email);
//             return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//         } catch (IOException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//         }
//     }
// }















// package com.example.demo.controller;
// import com.example.demo.service.ProfileService;

// import java.io.File;
// import java.io.IOException;

// import javax.annotation.Resource;

// import org.springframework.http.MediaType;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.FileSystemResource;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import org.springframework.http.HttpHeaders;

// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.ProfileService;

// @RestController
// @CrossOrigin
// //@RequestMapping("/api/profile")
// public class ProfileController {
  
//   @Autowired
//   private UserRepository userRepository;

//   @PostMapping("/picture/{email}")
//   public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {
//       // Validate that the file is an image file
      
//       if (!file.getContentType().startsWith("image/")) {
//           return ResponseEntity.badRequest().body("Only image files are allowed.");
//       }

//       try {
//           // Save the file to a temporary location on the server
//           File tempFile = File.createTempFile("profile-", ".jpg");
//           file.transferTo(tempFile);
        
//           // Update the user's profile picture in the database
//           User user = userRepository.findByEmail(email);
//           if (user == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//         }
    
//           user.setProfilePicture(tempFile.getAbsolutePath());
//           userRepository.save(user);
        
//           // Delete the temporary file
//           tempFile.delete();
        
//           return ResponseEntity.ok().body("Profile picture uploaded successfully.");
//       } catch (IOException ex) {
//           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile picture.");
//       }
//   }


//   //public ResponseEntity<Resource> getProfilePicture(@RequestParam("email") String email) {
//     @GetMapping("getprofile/{email}")
//     public ResponseEntity<String> getProfilePicture(@RequestParam String email) {
//     // Get the user from the database
//     User user = userRepository.findByEmail(email);
//     if (user == null) {
//       return ResponseEntity.notFound().build();
//   }
//     // Get the profile picture file from the server
//     File file = new File(user.getProfilePicture());
//     if (!file.exists()) {
//       return ResponseEntity.notFound().build();
//     }
//     // Create a Resource object from the file
//     Resource resource = (Resource) new FileSystemResource(file);
//     // Return the Resource in the response
//     return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(user.getProfilePicture());
//   }
// }










// @Autowired
// private ProfileService ProfileService;

// @PostMapping("/picture/{email}")
// public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file, @PathVariable("email") String email) {
//     try {
//         ProfileService.uploadProfilePicture(file, email);
//         return ResponseEntity.ok().body("Profile picture uploaded successfully.");
//     } catch (IOException ex) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile picture: " + ex.getMessage());
//     }
// }

// @GetMapping("/getprofile/{email}")
// public ResponseEntity<FileSystemResource> getProfilePicture(@PathVariable String email) {
//     try {
//         FileSystemResource resource = ProfileService.getProfilePicture(email);
//         return ResponseEntity.ok()
//             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//             .body(resource);
//     } catch (IOException ex) {
//         return ResponseEntity.notFound().build();
//     }
// }