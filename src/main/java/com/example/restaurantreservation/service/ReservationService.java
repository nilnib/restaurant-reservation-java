package com.example.restaurantreservation.service;

import com.example.restaurantreservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Reservation> getAll() {
        return mongoTemplate.findAll(Reservation.class);
    }

    public Optional<Reservation> getById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Reservation.class));
    }

    public Reservation create(Reservation reservation) {
        return mongoTemplate.insert(reservation);
    }

    public Reservation update(String id, Reservation reservation) {
        reservation.setId(id);
        return mongoTemplate.save(reservation);
    }

    public void delete(String id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), Reservation.class);
    }

    public List<Reservation> getByRestaurantAndTable(String restaurantId, int tableNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("restaurantId").is(restaurantId).and("tableNumber").is(tableNumber));
        return mongoTemplate.find(query, Reservation.class);
    }
}
