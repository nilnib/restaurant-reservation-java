package com.example.restaurantreservation.controller;

import com.example.restaurantreservation.model.Reservation;
import com.example.restaurantreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable String id) {
        Optional<Reservation> reservation = reservationService.getById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reservation reservation) {
        // Double-booking check (2-hour slot)
        List<Reservation> existing = reservationService.getByRestaurantAndTable(reservation.getRestaurantId(), reservation.getTableNumber());
        boolean overlap = existing.stream().anyMatch(r ->
                reservation.getReservationTime().isBefore(r.getReservationTime().plusHours(2)) &&
                r.getReservationTime().isBefore(reservation.getReservationTime().plusHours(2))
        );
        if (overlap) {
            return ResponseEntity.status(409).body("This table is already reserved for the selected time slot.");
        }
        Reservation created = reservationService.create(reservation);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Reservation reservation) {
        Optional<Reservation> existing = reservationService.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservation.setId(id);
        reservationService.update(id, reservation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Reservation> existing = reservationService.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
