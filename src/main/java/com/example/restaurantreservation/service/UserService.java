package com.example.restaurantreservation.service;

import com.example.restaurantreservation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAll() {
        return mongoTemplate.findAll(User.class);
    }

    public Optional<User> getById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, User.class));
    }

    public User create(User user) {
        return mongoTemplate.insert(user);
    }

    public User update(String id, User user) {
        user.setId(id);
        return mongoTemplate.save(user);
    }

    public void delete(String id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), User.class);
    }
}
