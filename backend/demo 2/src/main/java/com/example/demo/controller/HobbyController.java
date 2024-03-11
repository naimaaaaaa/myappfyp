package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.service.HobbyService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/hobby")
public class HobbyController {
    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private UserService userService;

    @PutMapping("/update-hobbies/user/{userId}")
    public ResponseEntity<List<Hobby>> updateSports(@PathVariable(value = "userId") long userId,
            @RequestBody List<Hobby> newHobbies) {

        Optional<User> existingUser = userService.findByID(userId);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        List<Hobby> hobbies = hobbyService.getHobbiesByUserId(userId);
        for (Hobby hobby : hobbies) {
            hobbyService.deleteHobbyById(hobby.getId());
        }
        for (Hobby newHobby : newHobbies) {
            newHobby.setUser(existingUser.get());
        }
        List<Hobby> newlyCreatedHobbies = hobbyService.saveAllHobbies(newHobbies);
        return ResponseEntity.ok(newlyCreatedHobbies);
    }
}