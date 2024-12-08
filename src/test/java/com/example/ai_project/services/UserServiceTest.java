package com.example.ai_project.services;

import com.example.myapp.entities.User;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null, "admin", "password", "ADMIN", true);
        when(userRepository.save(newUser)).thenReturn(
                new User(1L, "admin", "encodedPassword", "ADMIN", true)
        );

        User result = userService.createUser(newUser);
        assertEquals("admin", result.getUsername());
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.resetPassword(1L, "newPassword");
        });
    }
}
