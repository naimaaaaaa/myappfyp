package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.UserInfoController;
import com.example.demo.model.User;
import com.example.demo.service.UserConnectionsService;

public class GetSimilarUsersTest {

    @Mock
    private UserConnectionsService connectionsService;

    @InjectMocks
    private UserInfoController userInfoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSimilarUsers_Success() {
        // Arrange
        Long userId = 9L; 
        List<User> similarUsers = new ArrayList<>();
        // Assuming there are some similar users available
        User similarUser1 = new User();
        similarUser1.setId(10L); // Assuming similar user ID is 10
        similarUsers.add(similarUser1);

        // Mocking service method
        when(connectionsService.findSimilarUsers(userId)).thenReturn(similarUsers);

        // Act
        ResponseEntity<List<User>> responseEntity = userInfoController.getSimilarUsers(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(similarUsers.size(), responseEntity.getBody().size());
        assertEquals(similarUsers.get(0).getId(), responseEntity.getBody().get(0).getId());

        // Verify mock interactions
        verify(connectionsService, times(1)).findSimilarUsers(userId);
    }

    @Test
    public void testGetSimilarUsers_NoSimilarUsers() {
        // Arrange
        Long userId = 28L; // Assuming user ID is 28
        List<User> similarUsers = new ArrayList<>(); // Assuming no similar users available

        // Mocking service method
        when(connectionsService.findSimilarUsers(userId)).thenReturn(similarUsers);

        // Act
        ResponseEntity<List<User>> responseEntity = userInfoController.getSimilarUsers(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size()); // Expecting an empty list

        // Verify mock interactions
        verify(connectionsService, times(1)).findSimilarUsers(userId);
    }

    @Test
    public void testGetSimilarUsers_NullUserId() {
        // Arrange
        Long userId = null; // Null user ID

        // Act
        ResponseEntity<List<User>> responseEntity = userInfoController.getSimilarUsers(userId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Verify no interactions with the service
        verifyNoInteractions(connectionsService);
    }
}
