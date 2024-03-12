// package com.example.demo.service;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.Hobby;
// import com.example.demo.model.Society;
// import com.example.demo.model.Sport;
// import com.example.demo.model.User;
// import com.example.demo.model.UserConnections;
// import com.example.demo.model.UserExtra;
// import com.example.demo.repository.UserConnectionsRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.repository.UserExtraRepository;
// import com.example.demo.repository.HobbyRepository;
// import com.example.demo.repository.SocietyRepository;
// import com.example.demo.repository.SportRepository;
// import com.example.demo.repository.UserRepository;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class UserConnectionsService {
 
//     private final UserConnectionsRepository userConnectionsRepository;
//     private final UserService userService;
//     private final UserExtraRepository userExtraRepository;
//     private final HobbyRepository hobbyRepository;
//     private final SocietyRepository societyRepository;
//     private final SportRepository sportRepository;
//     private final UserRepository userRepository;

//     @Autowired
//     public UserConnectionsService(UserConnectionsRepository userConnectionsRepository, UserService userService, UserExtraRepository userExtraRepository,
//                                   HobbyRepository hobbyRepository, SocietyRepository societyRepository, SportRepository sportRepository, UserRepository userRepository) {
//         this.userConnectionsRepository = userConnectionsRepository;
//         this.userService = userService;
//         this.userExtraRepository = userExtraRepository;
//         this.hobbyRepository = hobbyRepository;
//         this.societyRepository = societyRepository;
//         this.sportRepository = sportRepository;
//         this.userRepository = userRepository;
//     }

//     public List<User> getUserConnections(Long userId) {
//         UserConnections userConnections = userConnectionsRepository.findByUserId(userId);
//         if (userConnections != null) {
//             return userConnections.getConnections();
//         }
//         return null;
//     }

  
//     // public List<User> findSimilarUsers(Long userId) {
//     //     List<User> similarUsers = new ArrayList<>();
//     //     UserConnections userConnections = userConnectionsRepository.findByUserId(userId);
//     //     if (userConnections == null) {
//     //         return similarUsers; // No connections found for the user
//     //     }
        
//     //     List<User> connections = userConnections.getConnections();
//     //     for (User connection : connections) {
//     //         List<User> connectionConnections = getUserConnections(connection.getId());
//     //         for (User user : connectionConnections) {
//     //             if (!user.getId().equals(userId) && !connections.contains(user) && !similarUsers.contains(user)) {
//     //                 similarUsers.add(user);
//     //             }
//     //         }
//     //     }
        
//     //     return similarUsers;
//     // }
//     public List<User> findSimilarUsers(Long userId) {
//         List<User> similarUsers = new ArrayList<>();
//         User user = userRepository.findById(userId)
//                                   .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

//         // Fetch hobbies, societies, sports from the user
//         List<Hobby> hobbies = user.getHobbies();
//         List<Society> societies = user.getSocieties();
//         List<Sport> sports = user.getSports();

//         // Find users with similar hobbies, societies, sports
//         for (Hobby hobby : hobbies) {
//             List<User> usersWithSimilarHobby = userRepository.findByHobby(hobby.getName());
//             similarUsers.addAll(usersWithSimilarHobby);
//         }

//         for (Society society : societies) {
//             List<User> usersWithSimilarSociety = userRepository.findBySociety(society.getName());
//             similarUsers.addAll(usersWithSimilarSociety);
//         }

//         for (Sport sport : sports) {
//             List<User> usersWithSimilarSport = userRepository.findBySport(sport.getName());
//             similarUsers.addAll(usersWithSimilarSport);
//         }

//         // Remove duplicates and the user itself from the list
//         similarUsers.remove(user);
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
