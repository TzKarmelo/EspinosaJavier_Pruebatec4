package com.Tourism_Agency.controller;

import com.Tourism_Agency.dto.BookingFlightDTO;
import com.Tourism_Agency.model.BookingFlight;
import com.Tourism_Agency.service.BookingFlightService;
import com.Tourism_Agency.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class BookingFlightController {

    @Autowired
    private BookingFlightService bookingFlightService;

    @Autowired
    private FlightService flightService;

    @GetMapping("/flight-booking/list")
    public ResponseEntity<Object> listFlightBookings() {
        try {
            List<BookingFlightDTO> bookingFlights = bookingFlightService.listBookingFlights();
            if (bookingFlights.isEmpty()) {
                return ResponseEntity.status(404).body("No flight bookings found.");
            } else {
                return ResponseEntity.ok(bookingFlights);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Failed to list flight bookings... " + e.getMessage());
        }
    }

    @PostMapping("/flight-booking/new")
    public ResponseEntity<String> newFlightBooking(@RequestBody BookingFlight bookingFlight) {
        try {
            bookingFlightService.saveBookingFlight(bookingFlight);
            return ResponseEntity.ok("New flight booking added!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Failed to add new flight booking... " + e.getMessage());
        }
    }

    @GetMapping("/flight-booking/{id}")
    public ResponseEntity<Object> listFlightBookingId(@PathVariable Long id) {
        try {
            BookingFlightDTO bookingFlight = bookingFlightService.listBookingFlightId(id);
            if (bookingFlight == null) {
                return ResponseEntity.status(404).body("Flight booking with id " + id + " not found.");
            } else {
                return ResponseEntity.ok(bookingFlight);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Failed to list flight booking... " + e.getMessage());
        }
    }

    @DeleteMapping("/flight-booking/delete/{id}")
    public ResponseEntity<String> deleteFlightBooking(@PathVariable Long id) {
        try {
            bookingFlightService.deleteBookingFlight(id);
            return ResponseEntity.ok("Flight booking deleted!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Failed to delete flight booking... " + e.getMessage());
        }
    }

}
