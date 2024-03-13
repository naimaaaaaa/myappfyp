package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Hobby;
import com.example.demo.model.Society;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.model.UserExtra;
import com.example.demo.service.HobbyService;
import com.example.demo.service.SocietyService;
import com.example.demo.service.SportService;
import com.example.demo.service.UserExtraService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

        @Autowired
        private UserService userService;

        @Autowired
        private UserExtraService userExtraService;

        @Autowired
        private HobbyService hobbyService;

        @Autowired
        private SocietyService societyService;

        @Autowired
        private SportService sportService;

        @PostMapping("/register")
        public ResponseEntity<User> registerUser(@RequestBody RegistrationDTO newRegisterDTO) {
                if ( newRegisterDTO.getName() == null ||
                                newRegisterDTO.getEmail() == null ||
                                newRegisterDTO.getPassword() == null) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                Optional<User> existingUser = userService.findByEmail(newRegisterDTO.getEmail());
                if ( existingUser.isPresent()) {
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                User newUser = new User(newRegisterDTO.getName(), newRegisterDTO.getEmail(),
                                encoder.encode(newRegisterDTO.getPassword()));
                User savedUser = userService.addUser(newUser);

                UserExtra newUserExtra = new UserExtra();
                newUserExtra.setCourse(newRegisterDTO.getCourse());
                newUserExtra.setEthnicity(newRegisterDTO.getEthnicity());
                newUserExtra.setUser(savedUser);

                List<String> hobbyNames = newRegisterDTO.getHobbies();
                List<Hobby> hobbies = hobbyNames.stream()
                                .map(hobbyName -> {
                                        Hobby hobby = new Hobby(hobbyName);
                                        hobby.setUser(savedUser);
                                        return hobby;
                                })
                                .collect(Collectors.toList());
                hobbyService.saveAllHobbies(hobbies);
                List<String> societyNames = newRegisterDTO.getSocieties();
                List<Society> societies = societyNames.stream()
                                .map(societyName -> {
                                        Society society = new Society(societyName);
                                        society.setUser(savedUser);
                                        return society;
                                })
                                .collect(Collectors.toList());
                societyService.saveAllSocieties(societies);
                List<String> sportNames = newRegisterDTO.getSports();
                List<Sport> sports = sportNames.stream()
                                .map(sportName -> {
                                        Sport sport = new Sport(sportName);
                                        sport.setUser(savedUser);
                                        return sport;
                                })
                                .collect(Collectors.toList());
                sportService.saveAllSports(sports);
                userExtraService.createUserExtraProfile(newUserExtra);
                return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }

}
