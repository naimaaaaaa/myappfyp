package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get All Users
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userService.findByID(id);
        return ResponseEntity.ok(user);
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @RequestBody User userDetails) {
        Optional<User> updatedUser = userService.updateUser(id, userDetails);
        return updatedUser.map(user -> ResponseEntity.ok(user)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a User by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    // Get User by Email
    @GetMapping("/findByEmail")
    public ResponseEntity<Optional<User>> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}