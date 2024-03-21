
package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Hobby;
import com.example.demo.model.Society;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.model.UserConnections;
import com.example.demo.repository.UserConnectionsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConnectionsService {

    private final UserConnectionsRepository userConnectionsRepository;
    private final UserRepository userRepository;
    private final HobbyService hobbyService;
    private final SportService sportService;
    private final SocietyService societyService;

    @Autowired
    public UserConnectionsService(UserConnectionsRepository userConnectionsRepository,
                                  UserRepository userRepository, HobbyService hobbyService,
                                  SportService sportService, SocietyService societyService) {
        this.userConnectionsRepository = userConnectionsRepository;
        this.userRepository = userRepository;
        this.hobbyService = hobbyService;
        this.sportService = sportService;
        this.societyService = societyService;
    }


    public List<User> findSimilarUsers(Long userId) {
        User user = userRepository.findById(userId)
                                   .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    
        List<User> similarUsers = new ArrayList<>();
        List<String> hobbies = hobbyService.getHobbiesByUserId(userId).stream().map(Hobby::getName).collect(Collectors.toList());
        List<String> societies = societyService.getSocietiesByUserId(userId).stream().map(Society::getName).collect(Collectors.toList());
        List<String> sports = sportService.getSportsByUserId(userId).stream().map(Sport::getName).collect(Collectors.toList());
        List<User> connections = getUserConnections(userId);
        // Check for users with at least two similar interests
        List<User> allUsers = (List<User>) userRepository.findAll();
        for (User otherUser : allUsers) {
            if (otherUser.getId() != userId && !connections.contains(otherUser)) { // Exclude the current user and already connected users
                int similarInterestCount = 0;
                List<String> otherHobbies = hobbyService.getHobbiesByUserId(otherUser.getId()).stream().map(Hobby::getName).collect(Collectors.toList());
                List<String> otherSocieties = societyService.getSocietiesByUserId(otherUser.getId()).stream().map(Society::getName).collect(Collectors.toList());
                List<String> otherSports = sportService.getSportsByUserId(otherUser.getId()).stream().map(Sport::getName).collect(Collectors.toList());
                similarInterestCount += countSimilarInterests(hobbies, otherHobbies);
                similarInterestCount += countSimilarInterests(societies, otherSocieties);
                similarInterestCount += countSimilarInterests(sports, otherSports);
    
                if (similarInterestCount >= 2) {
                    similarUsers.add(otherUser);
                }
            }
        }
    
        return similarUsers;
    }
    

    private int countSimilarInterests(List<String> interests1, List<String> interests2) {
        int count = 0;
        for (String interest : interests1) {
            if (interests2.contains(interest)) {
                count++;
            }
        }
        return count;
    }
    public void connectUsers(Long userId, Long selectedUserId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    
            User selectedUser = userRepository.findById(selectedUserId)
                    .orElseThrow(() -> new ResourceNotFoundException("Selected User", "id", selectedUserId));
    
           
            List<User> connections = getUserConnections(userId);
            if (connections.contains(selectedUser)) {
                throw new IllegalStateException("Users are already connected");
            }
            addConnection(user, selectedUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect users: " + e.getMessage());
        }
    }
   
    public List<User> getUserConnections(Long userId) {
        UserConnections userConnections = userConnectionsRepository.findByUserId(userId);
        if (userConnections != null) {
            return userConnections.getConnections();
        }
        return new ArrayList<>();
    }
    
    public void addConnection(User user, User connection) {
        UserConnections userConnections = userConnectionsRepository.findByUserId(user.getId());
        if (userConnections == null) {
            userConnections = new UserConnections(user, List.of(connection));
        } else {
            List<User> connections = userConnections.getConnections();
            connections.add(connection);
            userConnections.setConnections(connections);
        }
        userConnectionsRepository.save(userConnections);
    }

    public void deleteConnection(Long userId, Long connectionId) {
        UserConnections userConnections = userConnectionsRepository.findByUserId(userId);
        if (userConnections != null) {
            List<User> connections = userConnections.getConnections();
            connections.removeIf(user -> user.getId().equals(connectionId));
            userConnections.setConnections(connections);
            userConnectionsRepository.save(userConnections);
        }
    }
  
}






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

  
    

//     public List<User> findSimilarUsers(Long userId) {
//         User user = userRepository.findById(userId)
//                                   .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
//         List<User> similarUsers = new ArrayList<>();
    
//         // Fetch hobbies, societies, sports from the user
//         List<Hobby> hobbies = user.getHobbies();
//         List<Society> societies = user.getSocieties();
//         List<Sport> sports = user.getSports();
//         String course = user.getUserExtra().getCourse();
//         String ethnicity = user.getUserExtra().getEthnicity();
    
//         // Count similarities
//         int similarityCount;
//         for (User potentialMatch : userRepository.findAll()) {
//             similarityCount = 0;
    
//             // Check hobbies
//             for (Hobby hobby : hobbies) {
//                 if (potentialMatch.getHobbies().stream().anyMatch(h -> h.getName().equals(hobby.getName()))) {
//                     similarityCount++;
//                 }
//             }
    
//             // Check societies
//             for (Society society : societies) {
//                 if (potentialMatch.getSocieties().stream().anyMatch(s -> s.getName().equals(society.getName()))) {
//                     similarityCount++;
//                 }
//             }
    
//             // Check sports
//             for (Sport sport : sports) {
//                 if (potentialMatch.getSports().stream().anyMatch(sp -> sp.getName().equals(sport.getName()))) {
//                     similarityCount++;
//                 }
//             }
    
//             // Check course
//             if (potentialMatch.getUserExtra().getCourse().equals(course)) {
//                 similarityCount++;
//             }
    
//             // Check ethnicity
//             if (potentialMatch.getUserExtra().getEthnicity().equals(ethnicity)) {
//                 similarityCount++;
//             }
    
//             // Check if user has at least 2 similarities
//             if (similarityCount >= 2 && !potentialMatch.equals(user)) {
//                 similarUsers.add(potentialMatch);
//             }
//         }
    
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
