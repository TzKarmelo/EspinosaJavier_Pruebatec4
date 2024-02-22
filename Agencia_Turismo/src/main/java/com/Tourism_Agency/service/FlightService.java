package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.BookingFlightDTO;
import com.Tourism_Agency.dto.FlightDTO;
import com.Tourism_Agency.model.Flight;
import com.Tourism_Agency.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Flight editFlight(Flight flight) {
        Flight existingFlight = flightRepository.findById(flight.getCode()).orElse(null);
        if (existingFlight == null) {
            throw new IllegalArgumentException("Flight with number " + flight.getCode() + " not found.");
        }
        return flightRepository.save(flight);
    }

    @Override
    public Flight deleteFlight(String flightNumber) {
        Flight flight = flightRepository.findById(flightNumber).orElse(null);
        if (flight != null && flight.getBookings().isEmpty()) {
            flightRepository.delete(flight);
        }
        return flight;
    }

    @Override
    public boolean hasFlightReserves(String flightNumber) {
        Flight flight = flightRepository.findById(flightNumber).orElse(null);
        return flight != null && !flight.getBookings().isEmpty();
    }

    @Override
    public Flight listFlightId(String flightNumber) {

        Flight flight = flightRepository.findById(flightNumber).orElse(null);
        if (flight == null) {
            throw new IllegalArgumentException("Flight with number " + flightNumber + " not found.");
        }
        return flight;
    }

    @Override
    public Flight saveFlight(Flight flight) {

        if (flightRepository.findById(flight.getCode()).isPresent()) {
            throw new IllegalArgumentException("Flight with number " + flight.getCode() + " already exists.");
        }
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> listFlights() {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            throw new RuntimeException("No flights found");
        }
        return flights;
    }

    @Override
    public List<Flight> listFlightsByDateAndLocation(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        return flightRepository.findByDepartureDateBetweenAndOriginAndDestination(dateFrom, dateTo, origin, destination);
    }

    private FlightDTO convertToDTO(Flight flight) {
        return modelMapper.map(flight, FlightDTO.class);
    }

    public List<FlightDTO> listFlightsWithFilters(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        List<Flight> flights;
        if (dateFrom != null && dateTo != null && origin != null && destination != null) {
            flights = listFlightsByDateAndLocation(dateFrom, dateTo, origin, destination);
        } else {
            flights = flightRepository.findAll();
        }
        return flights.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
