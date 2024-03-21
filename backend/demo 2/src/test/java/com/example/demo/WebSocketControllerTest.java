package com.example.demo;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.demo.service.MessageService;
import com.example.demo.websocket.Message;
import com.example.demo.websocket.WebSocketController;
public class WebSocketControllerTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private WebSocketController webSocketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReceivePublicMessage() {
        // Arrange
        Message message = new Message();
        message.setSenderName("John");
        message.setReceiverName("Public Chat");
        message.setMessage("Hello, everyone!");
        message.setDate("2024-03-15");
        message.setStatus("MESSAGE");

        // Act
        webSocketController.receivePublicMessage(message);

        // Assert
        // Verify that the message is saved
        verify(messageService).saveMessage(message);

        // Verify that the message is sent to the correct destination
        verify(simpMessagingTemplate).convertAndSend("/ws/chatroom/public", message);
    }

    
}
