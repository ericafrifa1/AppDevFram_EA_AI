package com.example.ai_project.repositories;

import com.example.myapp.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User resetPassword(Long id, String newPassword);
    User toggleUnlocked(Long id);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
