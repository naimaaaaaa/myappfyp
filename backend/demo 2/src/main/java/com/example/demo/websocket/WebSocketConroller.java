package com.example.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketConroller {

    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(Message message) {
        // Handle incoming messages and process logic here
        // Ensure that only authenticated users can send messages
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Message processing logic
            return message;
        } else {
            // Handle unauthenticated user
            return null; // or throw exception
        }
    }

    @MessageMapping("/private-message")
    public void receivePrivateMessage(Message message) {
        // Ensure that only authenticated users can send private messages
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Send private message only to the intended recipient
            simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        } else {
            // Handle unauthenticated user
            // You may throw an exception or handle it accordingly
        }
    }
}



























// package com.example.demo.websocket;

// // import org.apache.logging.log4j.message.Message;
// import com.example.demo.websocket.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.stereotype.Controller;
// import org.springframework.messaging.handler.annotation.Payload;


// @Controller
// public class WebSocketConroller {

// @Autowired
// public SimpMessagingTemplate simpMessagingTemplate;


//     @MessageMapping("/message") //app/message
//     @SendTo("/chatroom/public")

//     public Message receivePublicMessage(@Payload Message message) {
//         // Handle incoming messages and process logic here
//         return message;
//     }

// @MessageMapping("/private-message")
// public Message receivePrivateMesage(@Payload Message message){

//     simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
//     return message;  

// }


// }



