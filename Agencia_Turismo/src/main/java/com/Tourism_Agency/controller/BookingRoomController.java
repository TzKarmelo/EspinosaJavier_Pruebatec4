package com.Tourism_Agency.controller;

import com.Tourism_Agency.model.BookingRoom;
import com.Tourism_Agency.service.IBookingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency")
public class BookingRoomController {

    @Autowired
    private IBookingRoomService roomBookingService;

    @PostMapping("/hotel-booking/new")
    public ResponseEntity<Object> newRoomBooking(@RequestBody BookingRoom roomBooking) {
        try {
            return ResponseEntity.ok(roomBookingService.addBookingRoom(roomBooking));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/hotel-booking/list")
    public ResponseEntity<Object> listRoomBookings() {
        try {
            return ResponseEntity.ok(roomBookingService.listBookingRooms());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/hotel-booking/{id}")
    public ResponseEntity<Object> listRoomBookingId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomBookingService.listBookingRoomId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/hotel-booking/edit/{id}")
    public ResponseEntity<Object> editRoomBooking(@PathVariable Long id, @RequestBody BookingRoom roomBooking) {
        try {
            roomBookingService.editBookingRoom(id, roomBooking);
            return ResponseEntity.ok("Room booking edited successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/hotel-booking/delete/{id}")
    public ResponseEntity<Object> deleteRoomBooking(@PathVariable Long id) {
        try {
            roomBookingService.deleteBookingRoom(id);
            return ResponseEntity.ok("Room booking deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
