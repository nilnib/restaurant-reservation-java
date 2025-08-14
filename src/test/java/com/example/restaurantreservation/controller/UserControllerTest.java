package com.example.restaurantreservation.controller;

import com.example.restaurantreservation.model.User;
import com.example.restaurantreservation.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Test
    void testGetUserById() {
        UserService mockService = mock(UserService.class);
        User user = new User();
        user.setId("1");
        user.setName("Test User");
        user.setEmail("test@example.com");
        when(mockService.getById("1")).thenReturn(Optional.of(user));
        UserController controller = new UserController();
        // inject mock
        try {
            java.lang.reflect.Field field = UserController.class.getDeclaredField("userService");
            field.setAccessible(true);
            field.set(controller, mockService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<User> response = controller.getById("1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test User", response.getBody().getName());
    }

    @Test
    void testGetAllUsers() {
        UserService mockService = mock(UserService.class);
        when(mockService.getAll()).thenReturn(List.of(new User()));
        UserController controller = new UserController();
        try {
            java.lang.reflect.Field field = UserController.class.getDeclaredField("userService");
            field.setAccessible(true);
            field.set(controller, mockService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<User> users = controller.getAll();
        assertEquals(1, users.size());
    }
}
