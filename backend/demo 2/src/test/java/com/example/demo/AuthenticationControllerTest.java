package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.AuthenticationController;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserExtra;
import com.example.demo.service.HobbyService;
import com.example.demo.service.SocietyService;
import com.example.demo.service.SportService;
import com.example.demo.service.UserExtraService;
import com.example.demo.service.UserService;

public class AuthenticationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserExtraService userExtraService;

    @Mock
    private HobbyService hobbyService;

    @Mock
    private SocietyService societyService;

    @Mock
    private SportService sportService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_ValidInput() {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setName("Tulip Flower");
        registrationDTO.setEmail("tulip@example.com");
        registrationDTO.setPassword("Tulip12345");
        registrationDTO.setCourse("Computer Science");
        registrationDTO.setEthnicity("Asian");
        registrationDTO.setHobbies(List.of("Reading", "Traveling"));
        registrationDTO.setSocieties(List.of("Chess Club", "Debate Society"));
        registrationDTO.setSports(List.of("Football", "Basketball"));
        when(userService.findByEmail("tulip@example.com")).thenReturn(Optional.empty()); 
        ResponseEntity<User> responseEntity = authenticationController.registerUser(registrationDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void testRegisterUser_ExistingUser() {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setName("Tulip Flower");
        registrationDTO.setEmail("tulip@example.com");
        registrationDTO.setPassword("Tulip12345");
        registrationDTO.setCourse("Computer Science");
        registrationDTO.setEthnicity("Asian");
        registrationDTO.setHobbies(List.of("Reading", "Traveling"));
        registrationDTO.setSocieties(List.of("Chess Club", "Debate Society"));
        registrationDTO.setSports(List.of("Football", "Basketball"));
        when(userService.findByEmail("tulip@example.com")).thenReturn(Optional.of(new User()));
        ResponseEntity<User> responseEntity = authenticationController.registerUser(registrationDTO);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    
    }
}
