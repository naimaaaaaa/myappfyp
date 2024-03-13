package com.example.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.example.demo.service.MessageService;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate, MessageService messageService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receivePublicMessage( Message message) {
            messageService.saveMessage(message);
            System.out.println("Public message received: " + message);
            return message;
    }

    @MessageMapping("/private-message")
    public void receivePrivateMessage(Message message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            messageService.saveMessage(message);
            simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        }
    }
}




// package com.example.demo.websocket;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Controller;
// import com.example.demo.service.MessageService;

// @Controller
// public class WebSocketController {

//     private final SimpMessagingTemplate simpMessagingTemplate;
//     private final MessageService messageService;

//     @Autowired
//     public WebSocketController(SimpMessagingTemplate simpMessagingTemplate, MessageService messageService) {
//         this.simpMessagingTemplate = simpMessagingTemplate;
//         this.messageService = messageService;
//     }

//     @MessageMapping("/message")
//     @SendTo("/chatroom/public")
//     public Message receivePublicMessage(Message message) {
//         messageService.saveMessage(message);
//         System.out.println("Public message received: " + message);
//         return message;
//     }

//     @MessageMapping("/private-message")
//     public void receivePrivateMessage(Message message) {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         if (authentication != null && authentication.isAuthenticated()) {
//             messageService.saveMessage(message);
//             // Ensure receiver name is correct
//             String receiverName = message.getReceiverName();
//             if (receiverName != null && !receiverName.isEmpty()) {
//                 simpMessagingTemplate.convertAndSendToUser(receiverName, "/private", message);
//                 System.out.println("Private message sent to: " + receiverName);
//             } else {
//                 System.err.println("Receiver name is empty or null");
//             }
//         } else {
//             System.err.println("User is not authenticated");
//         }
//     }
// }




























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



