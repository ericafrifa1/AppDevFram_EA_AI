package com.example.ai_project.controllers;

import com.example.myapp.entities.User;
import com.example.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}/reset-password")
    public User resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        return userService.resetPassword(id, newPassword);
    }

    @PutMapping("/{id}/toggle-unlocked")
    public User toggleUnlocked(@PathVariable Long id) {
        return userService.toggleUnlocked(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

