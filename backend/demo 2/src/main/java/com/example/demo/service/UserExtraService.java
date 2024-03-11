package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.UserExtra;
import com.example.demo.repository.UserExtraRepository;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class UserExtraService {

    @Autowired
    private UserExtraRepository userExtraRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserExtra> getUserExtra() {
        return (List<UserExtra>) userExtraRepository.findAll();
    }

    public UserExtra createUserExtraProfile(UserExtra userExtra) {
        return userExtraRepository.save(userExtra);
    }

    @Transactional(readOnly = true)
    public Optional<UserExtra> getUserExtraProfileByUserId(Long userId) {
        List<UserExtra> allUserExtras =  userExtraRepository.findAll();
        Optional<UserExtra> matchingUserExtra = allUserExtras.stream()
                .filter(userExtra -> userExtra.getUser().getId().equals(userId))
                .findFirst();

        return matchingUserExtra;
    }

    public Optional<UserExtra> updateUserExtraProfile(Long userId, UserExtra updatedUserExtra) {
        Optional<UserExtra> existingUserExtra = userExtraRepository.findById(userId);
        if (existingUserExtra.isPresent()) {
            UserExtra userExtra = existingUserExtra.get();
            userExtra.setCourse(updatedUserExtra.getCourse());
            userExtra.setEthnicity(updatedUserExtra.getEthnicity());
            return Optional.of(userExtraRepository.save(userExtra));
        } else {
            return Optional.empty();
        }
    }

    public void deleteUserExtraProfile(Long userId) {
        UserExtra userExtra = userExtraRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserExtra", "userId", userId));
        userExtraRepository.delete(userExtra);
    }
}

// package com.example.demo.service;

// import com.example.demo.model.UserExtra;
// import com.example.demo.repository.UserExtraRepository;

// import java.util.List;
// import java.util.Optional;

// import javax.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import com.example.demo.repository.UserRepository; // ADDED
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.User;

// @Transactional
// @Service
// public class UserExtraService {

//     @Autowired
//     private UserExtraRepository userExtraRepository;

//     @Autowired // ADDED
//     private UserRepository userRepository; // ADDED


//     // // Create a new user extra profile
//     // public UserExtra createUserExtraProfile(UserExtra userExtra) {
//     //     return userExtraRepository.save(userExtra);
//     // }

//  public UserExtra createUserExtraProfile(UserExtra userExtra) {
//         Long userId = userExtra.getUser().getId();

//         // Check if user already has an extra profile
//         UserExtra existingExtra = userExtraRepository.findByUserId(userId);
//         if (existingExtra != null) {
//             throw new IllegalStateException("User already has an extra profile");
//         }

//         return userExtraRepository.save(userExtra);
//     }

//     // Get user extra profile by user ID
//     public Optional<UserExtra> getUserExtraProfileByUserId(Long userId) {
//         return userExtraRepository.findById(userId);
//     }

//     // Get all user extra profiles
//     public List<UserExtra> getUserExtra() {
//         return (List<UserExtra>) userExtraRepository.findAll();
//     }
    
//     public UserExtra updateUserExtraProfile(Long userId, UserExtra updatedUserExtra) {
//         UserExtra existingUserExtra = userExtraRepository.findByUserId(userId);
//         if (existingUserExtra == null) {
//             throw new ResourceNotFoundException("User Extra", "ID", userId);
//         }
    
//         // Update the user extra profile
//         existingUserExtra.setCourse(updatedUserExtra.getCourse());
//         existingUserExtra.setHobbies(updatedUserExtra.getHobbies());
//         existingUserExtra.setSocieties(updatedUserExtra.getSocieties());
//         existingUserExtra.setSports(updatedUserExtra.getSports());
//         existingUserExtra.setEthnicity(updatedUserExtra.getEthnicity());
    
//         return userExtraRepository.save(existingUserExtra);
//     }

// public void deleteUserExtraProfile(Long userId) {
//     userExtraRepository.deleteByUserId(userId);
// }
// }
