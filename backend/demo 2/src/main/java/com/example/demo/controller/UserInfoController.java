
package com.example.demo.controller;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserInfoDTO;
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
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserService userService;
    private UserExtraService userExtraService;
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private SportService sportService;
    @Autowired
    private SocietyService societyService;
    @GetMapping("/")
    public ResponseEntity<UserInfoDTO> getUserInfoByEmail(@RequestParam String email) {
        if (email == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = userService.findByEmail(email);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            Optional<UserExtra> existingUserExtra = userExtraService.getUserExtraProfileByUserId(user.getId());
            List<Hobby> hobbies = hobbyService.getHobbiesByUserId(user.getId());
            System.out.println("User Hobbies: " + hobbies);
            List<Sport> sports = sportService.getSportsByUserId(user.getId());
            List<Society> societies = societyService.getSocietiesByUserId(user.getId());
            UserInfoDTO userDTO = new UserInfoDTO(user, hobbies, sports, societies, existingUserExtra.orElse(null));
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }}
