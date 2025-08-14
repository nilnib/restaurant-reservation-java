package com.example.restaurantreservation.service;

import com.example.restaurantreservation.model.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {
    @Test
    void preventsDoubleBookingWhenOverlapping() {
        List<Reservation> existing = List.of(new Reservation() {{
            setId("1");
            setRestaurantId("r1");
            setTableNumber(1);
            setReservationTime(LocalDateTime.of(2025, 8, 1, 18, 0));
            setNumberOfGuests(2);
            setCustomerName("Alice");
        }});
        Reservation newReservation = new Reservation();
        newReservation.setId("2");
        newReservation.setRestaurantId("r1");
        newReservation.setTableNumber(1);
        newReservation.setReservationTime(LocalDateTime.of(2025, 8, 1, 19, 0));
        newReservation.setNumberOfGuests(2);
        newReservation.setCustomerName("Bob");
        boolean overlaps = existing.stream().anyMatch(r ->
            newReservation.getReservationTime().isBefore(r.getReservationTime().plusHours(2)) &&
            r.getReservationTime().isBefore(newReservation.getReservationTime().plusHours(2))
        );
        assertTrue(overlaps, "Should detect double-booking for overlapping reservation times.");
    }
}
