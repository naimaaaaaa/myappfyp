//package com.example.demo;
package com.example.demo.test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.demo.websocket.Message;
import com.example.demo.websocket.WebSocketController;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
public class WebSocketControllerTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private WebSocketController webSocketController;

    @Test
    public void testReceivePublicMessage() {
        // Simulate an authenticated user
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        SecurityContext securityContext = org.mockito.Mockito.mock(SecurityContext.class);
        org.mockito.Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Simulate receiving a public message
        Message message = new Message();
        message.setContent("Test public message");
        webSocketController.receivePublicMessage(message);

        // Verify that the message is sent to the appropriate destination
        verify(simpMessagingTemplate).convertAndSend(any(String.class), any(Message.class));
    }

    @Test
    public void testReceivePrivateMessage() {
        // Simulate an authenticated user
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        SecurityContext securityContext = org.mockito.Mockito.mock(SecurityContext.class);
        org.mockito.Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Simulate receiving a private message
        Message message = new Message();
        message.setContent("Test private message");
        message.setReceiverName("testUser");
        webSocketController.receivePrivateMessage(message);

        // Verify that the message is sent to the appropriate user's private channel
        verify(simpMessagingTemplate).convertAndSendToUser("testUser", "/private", message);
    }
}
