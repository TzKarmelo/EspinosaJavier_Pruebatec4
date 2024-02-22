package com.Tourism_Agency.controller;

import com.Tourism_Agency.dto.BookingFlightDTO;
import com.Tourism_Agency.dto.FlightDTO;
import com.Tourism_Agency.model.Flight;
import com.Tourism_Agency.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/newFlight")
    public ResponseEntity<String> newFlight(@RequestBody Flight flight) {
        try {
            flightService.saveFlight(flight);
            return ResponseEntity.ok("New flight added!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add... " + e.getMessage());
        }
    }

    @GetMapping("/flights")
    public ResponseEntity<?> listFlights(
            @RequestParam(name = "dateFrom", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
            @RequestParam(name = "dateTo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
            @RequestParam(name = "origin", required = false) String origin,
            @RequestParam(name = "destination", required = false) String destination) {
        List<FlightDTO> flights = flightService.listFlightsWithFilters(dateFrom, dateTo, origin, destination);

        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flights found with the given filters.");
        } else {
            return ResponseEntity.ok(flights);
        }
    }

    @GetMapping("/flights/{flightNumber}")
    public ResponseEntity<?> listFlightId(@PathVariable String flightNumber) {
        Flight flight = flightService.listFlightId(flightNumber);

        if (flight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight with number " + flightNumber + " not found.");
        } else {
            return ResponseEntity.ok(flight);
        }
    }

    @PutMapping("/editFlight/{flightNumber}")
    public ResponseEntity<String> editFlight(@PathVariable String flightNumber, @RequestBody Flight flight) {
        try {
            flight.setCode(flightNumber);
            flightService.editFlight(flight);
            return ResponseEntity.ok("Flight edited!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to edit... " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteFlight/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
        try {
            flightService.deleteFlight(flightNumber);
            return ResponseEntity.ok("Flight deleted!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete... " + e.getMessage());
        }
    }

}
