package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.UserInfoController;
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

public class GetUserInfoTest {

    @Mock
    private UserService userService;

    @Mock
    private UserExtraService userExtraService;

    @Mock
    private HobbyService hobbyService;

    @Mock
    private SportService sportService;

    @Mock
    private SocietyService societyService;

    @InjectMocks
    private UserInfoController userInfoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserInfoByEmail_UserExists() {
        String email = "Naima@gmail.com";
        User user = new User();
        user.setId(1L); 
        user.setName("Naima");
        user.setEmail(email);
        Optional<User> existingUser = Optional.of(user);
        List<Hobby> hobbies = new ArrayList<>();
        List<Sport> sports = new ArrayList<>();
        List<Society> societies = new ArrayList<>();
        UserExtra userExtra = new UserExtra();
        when(userService.findByEmail(email)).thenReturn(existingUser);
        when(userExtraService.getUserExtraProfileByUserId(user.getId())).thenReturn(Optional.of(userExtra));
        when(hobbyService.getHobbiesByUserId(user.getId())).thenReturn(hobbies);
        when(sportService.getSportsByUserId(user.getId())).thenReturn(sports);
        when(societyService.getSocietiesByUserId(user.getId())).thenReturn(societies);
        ResponseEntity<UserInfoDTO> responseEntity = userInfoController.getUserInfoByEmail(email);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user.getId(), responseEntity.getBody().getId());
        assertEquals(user.getName(), responseEntity.getBody().getName());
        assertEquals(user.getEmail(), responseEntity.getBody().getEmail());
        assertEquals(hobbies, responseEntity.getBody().getHobbies());
        assertEquals(sports, responseEntity.getBody().getSports());
        assertEquals(societies, responseEntity.getBody().getSocieties());
        assertEquals(userExtra.getCourse(), responseEntity.getBody().getCourse());
        assertEquals(userExtra.getEthnicity(), responseEntity.getBody().getEthnicity());
    }

    @Test
    public void testGetUserInfoByEmail_UserNotFound() {
   
        String email = "nonexistent@example.com";
        Optional<User> existingUser = Optional.empty();
        when(userService.findByEmail(email)).thenReturn(existingUser);
        ResponseEntity<UserInfoDTO> responseEntity = userInfoController.getUserInfoByEmail(email);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
