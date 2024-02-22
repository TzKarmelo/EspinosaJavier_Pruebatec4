package com.Tourism_Agency.controller;

import com.Tourism_Agency.model.Hotel;
import com.Tourism_Agency.repository.HotelRepository;
import com.Tourism_Agency.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/agency")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/hotels/new")
    public ResponseEntity<String> addHotel(@RequestBody Hotel hotel) {
        try {
            hotelService.saveHotel(hotel);
            return ResponseEntity.ok("Hotel with code " + hotel.getHotelId() + " added.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/hotels")
    public ResponseEntity<?> listHotels(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo

    ) {
        try {
            return ResponseEntity.ok(hotelService.listHotelsWithFilters(city, dateFrom, dateTo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Object> listHotelId(@PathVariable Long hotelId) {
        try {
            return ResponseEntity.ok(hotelService.listHotelId(String.valueOf(hotelId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/hotels/edit/{hotelId}")
    public ResponseEntity<String> editHotel(@PathVariable Long hotelId, @RequestBody Hotel hotel) {
        try {
            hotelService.editHotel(hotel);
            return ResponseEntity.ok("Hotel with code " + hotel.getHotelId() + " edited.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/hotels/delete/{hotelId}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long hotelId) {
        try {
            hotelService.deleteHotel(hotelId);
            return ResponseEntity.ok("Hotel with code " + hotelId + " deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
