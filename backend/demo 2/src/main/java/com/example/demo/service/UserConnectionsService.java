// package com.example.demo.service;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.User;
// import com.example.demo.model.UserConnections;
// import com.example.demo.model.UserExtra;
// import com.example.demo.repository.UserConnectionsRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.repository.UserExtraRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserConnectionsService {
 
//     private final UserConnectionsRepository userConnectionsRepository;
//     private final UserService userService;
//     private final UserExtraRepository userExtraRepository;

//     @Autowired
//     public UserConnectionsService(UserConnectionsRepository userConnectionsRepository, UserService userService, UserExtraRepository userExtraRepository) {
//         this.userConnectionsRepository = userConnectionsRepository;
//         this.userService = userService;
//         this.userExtraRepository = userExtraRepository; // Initialize userExtraRepository
//     }

//     public List<User> getUserConnections(Long userId) {
//         UserConnections userConnections = userConnectionsRepository.findByUserId(userId);
//         if (userConnections != null) {
//             return userConnections.getConnections();
//         }
//         return null;
//     }
    
//     public List<UserExtra> findSimilarUsers(Long userId) {
//         UserExtra userExtra = userExtraRepository.findByUserId(userId);
//         if (userExtra == null) {
//             throw new ResourceNotFoundException("UserExtra", "userId", userId);
//         }

//         // Implement logic to find similar users based on similarities like hobbies, societies, courses, ethnicity, and sports
//         List<UserExtra> similarUsers = userExtraRepository.findSimilarUsers(
//             userExtra.getHobbies(), 
//             userExtra.getSocieties(), 
//             userExtra.getCourse(), 
//             userExtra.getEthnicity(), 
//             userExtra.getSports()
//         );

//         return similarUsers;
//     }



//     public void addConnection(User user, User connection) {
//         UserConnections userConnections = userConnectionsRepository.findByUserId(user.getId());
//         if (userConnections == null) {
//             userConnections = new UserConnections(user, List.of(connection));
//         } else {
//             List<User> connections = userConnections.getConnections();
//             connections.add(connection);
//             userConnections.setConnections(connections);
//         }
//         userConnectionsRepository.save(userConnections);
//     }

//     public void removeConnection(User user, User connection) {
//         UserConnections userConnections = userConnectionsRepository.findByUserId(user.getId());
//         if (userConnections != null) {
//             List<User> connections = userConnections.getConnections();
//             connections.remove(connection);
//             userConnections.setConnections(connections);
//             userConnectionsRepository.save(userConnections);
//         }
//     }
// }
