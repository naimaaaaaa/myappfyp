// package com.example.demo.service;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.net.MalformedURLException;
// import java.net.http.HttpHeaders;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;
// import java.nio.file.Path;

// import javax.annotation.Resource;
// import javax.transaction.Transactional;

// import com.example.demo.model.User;

// import org.apache.tomcat.util.http.parser.MediaType;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.FileSystemResource;
// import org.springframework.core.io.UrlResource;
// import org.springframework.http.ContentDisposition;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.UserRepository;

// import antlr.StringUtils;

// import java.util.Optional;



// @Service
// public class ProfileService {
  
//   @Autowired
//   private UserRepository userRepository;
 
//   @Transactional

//   public void uploadProfilePicture(MultipartFile file, String email) throws IOException {
//     // Validate that the file is an image file
//     if (!file.getContentType().startsWith("image/")) {
//       throw new IllegalArgumentException("Only image files are allowed.");
//     }
    
//     // Save the file to a temporary location on the server
//     // File tempFile = File.createTempFile("profile-", ".jpg");
//     // file.transferTo(tempFile);

//     String fileName = email + "_" + System.currentTimeMillis() + ".jpg";
//         Path uploadPath = Paths.get("uploads");
//         Path filePath = uploadPath.resolve(fileName);
//         file.transferTo(filePath);
        
//     try {
//       // Update the user's profile picture in the database
//       User user = userRepository.findByEmail(email);
//       if (user==null) {
//           throw new IllegalArgumentException("User not found.");
//       }
      
//     //   user.setProfilePicture(tempFile.getAbsolutePath());
//      user.setProfilePicture(fileName);
//       userRepository.save(user);
//     } catch (Exception ex) {
//       // Delete the temporary file if an error occurs
//      // tempFile.delete();
//       Files.delete(filePath);
//       throw ex;
//     }
    
//     // Delete the temporary file
//    // tempFile.delete();
// }



// public FileSystemResource getProfilePicture(String email) throws IOException {
//   User user = userRepository.findByEmail(email);
//   if (user == null) {
//       throw new IllegalArgumentException("User profile picture not found.");
//   }
  
//   // Get the profile picture file from the server
//   //File file = new File(user.getProfilePicture());
//   Path filePath = Paths.get("uploads", user.getProfilePicture());
//   FileSystemResource resource = new FileSystemResource(filePath);
//   if (!resource.exists()) {
//     throw new ResourceNotFoundException("User profile picture not found.", email, resource);
//   }
  
//   // Create a Resource object from the file
//  // FileSystemResource resource = new FileSystemResource(file);
  
//   return resource;
// }




// }







// if (!file.getContentType().startsWith("image/")) {
//   throw new IllegalArgumentException("Only image files are allowed.");
// }

// String fileName = email + "_" + System.currentTimeMillis() + ".jpg";
// Path uploadPath = Paths.get("uploads");
// Path filePath = uploadPath.resolve(fileName);
// Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

// User user = userRepository.findByEmail(email);
// if (user == null) {
//   throw new IllegalArgumentException("User not found.");
// }

// user.setProfilePicture(fileName);
// userRepository.save(user);
// }

// public FileSystemResource getProfilePicture(String email) throws IOException {
// User user = userRepository.findByEmail(email);
// if (user == null || user.getProfilePicture() == null || user.getProfilePicture().trim().isEmpty()) {
//   throw new ResourceNotFoundException("User profile picture not found.", email, user);
// }

// Path filePath = Paths.get("uploads", user.getProfilePicture());
// FileSystemResource resource = new FileSystemResource(filePath);
// if (!resource.exists()) {
//   throw new ResourceNotFoundException("User profile picture not found.", email, resource);
// }

// return resource;
// }
