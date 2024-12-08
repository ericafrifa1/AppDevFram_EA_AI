package com.example.ai_project.controllers;

import com.example.myapp.entities.User;
import com.example.myapp.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null, "admin", "password", "ADMIN", true);
        when(userService.createUser(newUser)).thenReturn(
                new User(1L, "admin", "encodedPassword", "ADMIN", true)
        );

        User result = userController.createUser(newUser);
        assertEquals(1L, result.getId());
        assertEquals("admin", result.getUsername());
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(
                List.of(new User(1L, "admin", "password", "ADMIN", true))
        );

        List<User> result = userController.getAllUsers();
        assertEquals(1, result.size());
        assertEquals("admin", result.get(0).getUsername());
    }
}
