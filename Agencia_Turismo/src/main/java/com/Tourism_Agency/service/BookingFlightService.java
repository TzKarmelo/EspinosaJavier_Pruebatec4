package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.BookingFlightDTO;
import com.Tourism_Agency.model.BookingFlight;
import com.Tourism_Agency.model.Flight;
import com.Tourism_Agency.repository.BookingFlightRepository;
import com.Tourism_Agency.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingFlightService implements IBookingFlightService{

    @Autowired
    private BookingFlightRepository bookingFlightRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IFlightService flightService;

    @Autowired
    private AppUserService appUserService;
    @Override
    public List<BookingFlightDTO> listBookingFlights() {
        List<BookingFlight> bookingFlights = bookingFlightRepository.findAll();
        if (bookingFlights.isEmpty()) {
            throw new IllegalArgumentException("No booking flights found.");
        }
        return bookingFlights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BookingFlightDTO convertToDTO(BookingFlight flightBooking) {
        return modelMapper.map(flightBooking, BookingFlightDTO.class);
    }

    @Override
    public BookingFlightDTO listBookingFlightId(Long id) {

        BookingFlight bookingFlight = bookingFlightRepository.findById(id).orElse(null);
        if (bookingFlight == null) {
            throw new IllegalArgumentException("Booking flight not found for id: " + id);
        }
        return convertToDTO(bookingFlight);
    }

    @Override
    public ResponseEntity<Object> deleteBookingFlight(Long id) {

        BookingFlight reserve = bookingFlightRepository.findById(id).orElse(null);
        if (reserve == null) {
            throw new IllegalArgumentException("Booking flight not found for id: " + id);
        }
        bookingFlightRepository.deleteById(id);
        return ResponseEntity.status(200).body("Booking flight deleted successfully!");
    }

    @Override
    public ResponseEntity<Object> saveBookingFlight(BookingFlight bookingFlight) {

        Flight validatedFlight = validateFlight(bookingFlight);
        Double totalCost = calculateTotalCost(bookingFlight, validatedFlight);
        avaliableSeats(validatedFlight, bookingFlight.getPassengers().size());
        bookingFlightRepository.save(bookingFlight);
        return ResponseEntity.status(200).body("Booking flight saved successfully! Total cost: " + totalCost);
    }

    private Flight validateFlight(BookingFlight bookingFlight) {
        String flightId = bookingFlight.getFlight().getCode();
        Flight flight = flightService.listFlightId(flightId);
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found!");
        }
        return flight;
    }

    private Double calculateTotalCost(BookingFlight bookingFlight, Flight flight) {
        return flight.getPrice() * bookingFlight.getPassengers().size();
    }

    private void avaliableSeats(Flight flight, int passengers) {
        if (flight.getSeats() < passengers) {
            throw new IllegalArgumentException("Not enough available seats!");
        }
        flight.setSeats(flight.getSeats() - passengers);
    }

}
