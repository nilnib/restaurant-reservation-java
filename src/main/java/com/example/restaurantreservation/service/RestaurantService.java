package com.example.restaurantreservation.service;

import com.example.restaurantreservation.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Restaurant> getAll() {
        return mongoTemplate.findAll(Restaurant.class);
    }

    public Optional<Restaurant> getById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Restaurant.class));
    }

    public Restaurant create(Restaurant restaurant) {
        return mongoTemplate.insert(restaurant);
    }

    public Restaurant update(String id, Restaurant restaurant) {
        restaurant.setId(id);
        return mongoTemplate.save(restaurant);
    }

    public void delete(String id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), Restaurant.class);
    }
}
