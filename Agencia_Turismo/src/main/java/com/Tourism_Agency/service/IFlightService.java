package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.FlightDTO;
import com.Tourism_Agency.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IFlightService {

    public Flight editFlight(Flight flight);
    public Flight deleteFlight(String flightNumber);
    public boolean hasFlightReserves(String flightNumber);
    public Flight listFlightId(String flightNumber);

    public Flight saveFlight(Flight flight);

    public List<Flight> listFlights();

    public List<Flight> listFlightsByDateAndLocation(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);

}
