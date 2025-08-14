package com.example.restaurantreservation.service;

import com.example.restaurantreservation.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAndGetUser() {
        User user = new User();
        user.setId("1");
        user.setName("Test User");
        user.setEmail("test@example.com");
        when(mongoTemplate.insert(user)).thenReturn(user);
        User created = userService.create(user);
        assertEquals("1", created.getId());
        assertEquals("Test User", created.getName());
        assertEquals("test@example.com", created.getEmail());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User());
        when(mongoTemplate.findAll(User.class)).thenReturn(users);
        List<User> result = userService.getAll();
        assertEquals(1, result.size());
    }
}
