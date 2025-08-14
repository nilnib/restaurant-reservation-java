package com.example.restaurantreservation.controller;

import com.example.restaurantreservation.model.Restaurant;
import com.example.restaurantreservation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@PathVariable String id) {
        Optional<Restaurant> restaurant = restaurantService.getById(id);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Restaurant restaurant) {
        Optional<Restaurant> existing = restaurantService.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        restaurant.setId(id);
        restaurantService.update(id, restaurant);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Restaurant> existing = restaurantService.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
