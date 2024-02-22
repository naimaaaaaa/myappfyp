package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    public Optional<User> findByID(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Add methods for profile functionality

    // public void updateProfilePicture(MultipartFile file, String email) throws IOException {
	// 	User user = userRepository.findByEmail(email);
    //     if (user != null) {
    //         // Save the profile picture bytes to the user entity
    //         user.setProfilePicture(file.getBytes());
    //         userRepository.save(user);
    //     } else {
    //         throw new IllegalArgumentException("User with email " + email + " not found.");
    //     }
    // }

	// public byte[] getProfilePicture(String email) throws IOException {
    //     User user = userRepository.findByEmail(email);
    //     if (user != null && user.getProfilePicture() != null) {
    //         return user.getProfilePicture();
    //     } else {
    //         throw new IllegalArgumentException("Profile picture not found for user with email " + email);
    //     }
    // }
}








// package com.example.demo.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// // import com.example.demo.model.ProduceSellerPrice;
// import com.example.demo.model.User;
// //import com.example.demo.repository.ProduceSellerPriceRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.UserType;
// import com.example.demo.exception.ResourceNotFoundException;

// @Service
// public class UserService {
// 	@Autowired
//     UserRepository userRepository;
	
// 	@Autowired
// 	//private ProduceSellerPriceRepository priceRepository;
	
// 	public UserService() {
// 		super();
// 		// TODO Auto-generated constructor stub
// 	}
	
	
// 	public List<User> getUsers() {
// 		return (List<User>) userRepository.findAll();
// 	}

	
// 	public void addUser(User newUser) {
// 		userRepository.save(newUser);
// 	}
	
// 	public Optional<User> findByID(Long id) {
// 		 return userRepository.findById(id);
// 	}
	
// 	@Transactional
// 	public void deleteUser(Long id) {
// 		User user = userRepository.findById(id)
// 				  .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		
		
// 		// if (user.getUserType() == UserType.SELLER || user.getUserType() == UserType.BOTH) {
// 		// 	Iterable<ProduceSellerPrice> deleted_prices= priceRepository.deleteBySeller(user);
// 		// 	deleted_prices.forEach((p)->{
// 		// 		System.out.println(p);
// 		// 	});
// 		// }
		
// 		userRepository.delete(user);
// 	} 
	
// 	public User findByEmail(String email) {
// 		return userRepository.findByEmail(email);
// 	}

// }
