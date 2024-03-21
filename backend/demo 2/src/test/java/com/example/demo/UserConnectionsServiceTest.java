package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserConnections;
import com.example.demo.repository.UserConnectionsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HobbyService;
import com.example.demo.service.SocietyService;
import com.example.demo.service.SportService;
import com.example.demo.service.UserConnectionsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserConnectionsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConnectionsRepository userConnectionsRepository;

    @Mock
    private HobbyService hobbyService;

    @Mock
    private SportService sportService;

    @Mock
    private SocietyService societyService;

    @InjectMocks
    private UserConnectionsService userConnectionsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addConnection_ExistingUserConnections() {
        Long userId = 1L;
        Long connectionId = 2L;
        User user = new User();
        user.setId(userId);
        User connection = new User();
        connection.setId(connectionId);
        UserConnections userConnections = new UserConnections(user, new ArrayList<>());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userConnectionsRepository.findByUserId(userId)).thenReturn(userConnections);
        userConnectionsService.addConnection(user, connection);
        verify(userConnectionsRepository, times(1)).save(any(UserConnections.class));
    }

    @Test
    void deleteConnection_UserConnectionsExist() {
        Long userId = 1L;
        Long connectionId = 2L;
        User user = new User();
        user.setId(userId);
        User connection = new User();
        connection.setId(connectionId);
        List<User> connections = new ArrayList<>();
        connections.add(connection);
        UserConnections userConnections = new UserConnections(user, connections);
        when(userConnectionsRepository.findByUserId(userId)).thenReturn(userConnections);
        userConnectionsService.deleteConnection(userId, connectionId);
        verify(userConnectionsRepository, times(1)).save(any(UserConnections.class));
    }

    @Test
    void deleteConnection_UserConnectionsDoNotExist() {
        Long userId = 1L;
        Long connectionId = 2L;
        when(userConnectionsRepository.findByUserId(userId)).thenReturn(null);
        userConnectionsService.deleteConnection(userId, connectionId);
        verify(userConnectionsRepository, never()).save(any(UserConnections.class));
    }
}
