package com.example.restaurantreservation.service;

import com.example.restaurantreservation.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAndGetRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId("1");
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Main St");
        when(mongoTemplate.insert(restaurant)).thenReturn(restaurant);
        Restaurant created = restaurantService.create(restaurant);
        assertEquals("1", created.getId());
        assertEquals("Test Restaurant", created.getName());
        assertEquals("123 Main St", created.getAddress());
    }

    @Test
    void testGetAllRestaurants() {
        List<Restaurant> restaurants = List.of(new Restaurant());
        when(mongoTemplate.findAll(Restaurant.class)).thenReturn(restaurants);
        List<Restaurant> result = restaurantService.getAll();
        assertEquals(1, result.size());
    }
}
