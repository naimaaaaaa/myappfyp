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

import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.service.SportService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/sport")
public class SportController {
    @Autowired
    private SportService sportService;

    @Autowired
    private UserService userService;

    @PutMapping("/update-sports/user/{userId}")
    public ResponseEntity<List<Sport>> updateSports(@PathVariable(value = "userId") long userId,
            @RequestBody List<Sport> newSports) {

        Optional<User> existingUser = userService.findByID(userId);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        List<Sport> sports = sportService.getSportsByUserId(userId);
        for (Sport sport : sports) {
            sportService.deleteSportById(sport.getId());
        }
        for (Sport newSport : newSports) {
            newSport.setUser(existingUser.get());
        }
        List<Sport> newlyCreatedSports = sportService.saveAllSports(newSports);
        return ResponseEntity.ok(newlyCreatedSports);
    }
}
