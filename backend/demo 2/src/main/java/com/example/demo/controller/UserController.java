package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserPostDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController  
public class UserController {
	
	@Autowired
	UserService userService;


	// Get All Users
    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    @PostMapping("/user")
    public ResponseEntity<Optional<User>> addUser(@RequestBody UserPostDTO newUserDTO) {
    	
    	if (newUserDTO.getName()==null || 
    		newUserDTO.getEmail()==null ||
    		newUserDTO.getPassword()==null) {
    		//newUserDTO.getUserType() == null) {
            return new ResponseEntity<>(Optional.ofNullable(null), HttpStatus.BAD_REQUEST);
        }
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	User newUser = new User(newUserDTO.getName(), newUserDTO.getEmail(),
    			encoder.encode(newUserDTO.getPassword()) );
    	userService.addUser(newUser);
    	return new ResponseEntity<>(Optional.ofNullable(newUser),HttpStatus.CREATED);

    }
	 
    
    // Get User by ID
    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") long Id) {
        return userService.findByID(Id);
    }
    
    
    //Delete a User by ID
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable(value = "id") long Id) {
        userService.deleteUser(Id);
        return "User Deleted"; 
    }
    
    //Get User by Email
    @GetMapping("/user/findByEmail")
    public Optional<User> getUserByEmail(@RequestParam String email) {
    	return Optional.ofNullable(userService.findByEmail(email));
    }
//Get user by name
    @GetMapping("/get-name")
    public ResponseEntity<String> getNameByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user.getName());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    

}