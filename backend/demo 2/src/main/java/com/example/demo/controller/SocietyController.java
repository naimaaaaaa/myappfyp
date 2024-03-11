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

import com.example.demo.model.Society;
import com.example.demo.model.User;
import com.example.demo.service.SocietyService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/society")
public class SocietyController {
    @Autowired
    private SocietyService societyService;

    @Autowired
    private UserService userService;

    @PutMapping("/update-societies/user/{userId}")
    public ResponseEntity<List<Society>> updateSports(@PathVariable(value = "userId") long userId,
            @RequestBody List<Society> newSocieties) {

        Optional<User> existingUser = userService.findByID(userId);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        List<Society> societies = societyService.getSocietiesByUserId(userId);
        for (Society society : societies) {
            societyService.deleteSocietyById(society.getId());
        }
        for (Society newSociety : newSocieties) {
            newSociety.setUser(existingUser.get());
        }
        List<Society> newlyCreatedSocieties = societyService.saveAllSocieties(newSocieties);
        return ResponseEntity.ok(newlyCreatedSocieties);
    }
}
