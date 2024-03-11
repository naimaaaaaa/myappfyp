package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User addUser(User newUser) {
        return userRepository.save(newUser);
    }

    public Optional<User> findByID(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return Optional.of(userRepository.save(user));
        } else {
            return Optional.empty();
        }
    }

    public void saveHobbies(Long userId, List<String> hobbies) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));
        List<Hobby> hobbyEntities = hobbies.stream()
                .map(Hobby::new)
                .collect(Collectors.toList());
        user.setHobbies(hobbyEntities);
        userRepository.save(user);
    }

}
// package com.example.demo.service;

// import java.io.IOException;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.exception.ResourceNotFoundException;

// @Service
// public class UserService {
//     @Autowired
//     private UserRepository userRepository;

//     public List<User> getUsers() {
//         return (List<User>) userRepository.findAll();
//     }

//     public void addUser(User newUser) {
//         userRepository.save(newUser);
//     }

//     public Optional<User> findById(Long id) {
//         return userRepository.findById(id);
//     }

//     @Transactional
//     public void deleteUser(Long id) {
//         User user = userRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

//         userRepository.delete(user);
//     }

//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email);
//     }

// }
