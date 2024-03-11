// package com.example.demo.controller;

// import com.example.demo.model.User;
// import com.example.demo.model.UserExtra;
// import com.example.demo.service.UserConnectionsService;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/connections")
// public class UserConnectionsController {

//     private final UserConnectionsService connectionsService;
//     private final UserService userService;

//     @Autowired
//     public UserConnectionsController(UserConnectionsService connectionsService, UserService userService) {
//         this.connectionsService = connectionsService;
//         this.userService = userService;
//     }

//     // Get connections for a specific user
//     @GetMapping("/{userId}")
//     public ResponseEntity<List<User>> getUserConnections(@PathVariable Long userId) {
//         List<User> connections = connectionsService.getUserConnections(userId);
//         return new ResponseEntity<>(connections, HttpStatus.OK);
//     }


//     // UserConnectionsController.java
// @GetMapping("/connections/similar")
// public ResponseEntity<List<UserExtra>> getSimilarUsers(@RequestParam Long userId) {
//     List<UserExtra> similarUsers = connectionsService.findSimilarUsers(userId);
//     return ResponseEntity.ok(similarUsers);
// }

//     // Add connection for a user
//     @PostMapping("/{userId}/add")
//     public ResponseEntity<String> addConnection(@PathVariable Long userId, @RequestParam Long connectionId) {
//         User user = userService.findById(userId)
//                 .orElse(null);
//         User connection = userService.findById(connectionId)
//                 .orElse(null);
//         if (user == null || connection == null) {
//             return new ResponseEntity<>("User or connection not found", HttpStatus.NOT_FOUND);
//         }
//         connectionsService.addConnection(user, connection);
//         return new ResponseEntity<>("Connection added successfully", HttpStatus.OK);
//     }

//     // Remove connection for a user
//     @PostMapping("/{userId}/remove")
//     public ResponseEntity<String> removeConnection(@PathVariable Long userId, @RequestParam Long connectionId) {
//         User user = userService.findById(userId)
//                 .orElse(null);
//         User connection = userService.findById(connectionId)
//                 .orElse(null);
//         if (user == null || connection == null) {
//             return new ResponseEntity<>("User or connection not found", HttpStatus.NOT_FOUND);
//         }
//         connectionsService.removeConnection(user, connection);
//         return new ResponseEntity<>("Connection removed successfully", HttpStatus.OK);
//     }
// }
