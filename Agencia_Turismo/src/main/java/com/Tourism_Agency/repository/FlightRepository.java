package com.Tourism_Agency.repository;

import com.Tourism_Agency.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByDepartureDateBetweenAndOriginAndDestination(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);
}
