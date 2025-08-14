package com.example.restaurantreservation.controller;

import com.example.restaurantreservation.model.Restaurant;
import com.example.restaurantreservation.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {
    @Test
    void testGetRestaurantById() {
        RestaurantService mockService = mock(RestaurantService.class);
        Restaurant restaurant = new Restaurant();
        restaurant.setId("1");
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Main St");
        when(mockService.getById("1")).thenReturn(Optional.of(restaurant));
        RestaurantController controller = new RestaurantController();
        // inject mock
        try {
            java.lang.reflect.Field field = RestaurantController.class.getDeclaredField("restaurantService");
            field.setAccessible(true);
            field.set(controller, mockService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Restaurant> response = controller.getById("1");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Restaurant", response.getBody().getName());
    }

    @Test
    void testGetAllRestaurants() {
        RestaurantService mockService = mock(RestaurantService.class);
        when(mockService.getAll()).thenReturn(List.of(new Restaurant()));
        RestaurantController controller = new RestaurantController();
        try {
            java.lang.reflect.Field field = RestaurantController.class.getDeclaredField("restaurantService");
            field.setAccessible(true);
            field.set(controller, mockService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Restaurant> restaurants = controller.getAll();
        assertEquals(1, restaurants.size());
    }
}
