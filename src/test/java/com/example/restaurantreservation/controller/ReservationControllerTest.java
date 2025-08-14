package com.example.restaurantreservation.controller;

import com.example.restaurantreservation.model.Reservation;
import com.example.restaurantreservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {
    @Test
    void returnsConflictWhenDoubleBookingAttempted() {
        ReservationService mockService = mock(ReservationService.class);
        String restaurantId = "r1";
        int tableNumber = 1;
        List<Reservation> existing = List.of(new Reservation() {{
            setId("1");
            setRestaurantId(restaurantId);
            setTableNumber(tableNumber);
            setReservationTime(LocalDateTime.of(2025, 8, 1, 18, 0));
            setNumberOfGuests(2);
            setCustomerName("Alice");
        }});
        when(mockService.getByRestaurantAndTable(restaurantId, tableNumber)).thenReturn(existing);
        ReservationController controller = new ReservationController();
// Use reflection to inject the mock since reservationService is private and no setter exists
try {
    java.lang.reflect.Field field = ReservationController.class.getDeclaredField("reservationService");
    field.setAccessible(true);
    field.set(controller, mockService);
} catch (Exception e) {
    throw new RuntimeException(e);
}
        Reservation newReservation = new Reservation();
        newReservation.setId("2");
        newReservation.setRestaurantId(restaurantId);
        newReservation.setTableNumber(tableNumber);
        newReservation.setReservationTime(LocalDateTime.of(2025, 8, 1, 19, 0)); // Overlaps
        newReservation.setNumberOfGuests(2);
        newReservation.setCustomerName("Bob");
        ResponseEntity<?> response = controller.create(newReservation);
        assertEquals(409, response.getStatusCodeValue());
        assertEquals("This table is already reserved for the selected time slot.", response.getBody());
    }
}
