package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.BookingFlightDTO;
import com.Tourism_Agency.model.BookingFlight;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBookingFlightService {

    public List<BookingFlightDTO> listBookingFlights();
    public BookingFlightDTO listBookingFlightId(Long id);
    public ResponseEntity<Object> deleteBookingFlight(Long id);
    public ResponseEntity<Object> saveBookingFlight(BookingFlight bookingFlight);

}
