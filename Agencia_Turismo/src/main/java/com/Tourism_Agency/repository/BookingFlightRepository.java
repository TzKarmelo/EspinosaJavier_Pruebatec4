package com.Tourism_Agency.repository;

import com.Tourism_Agency.model.BookingFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingFlightRepository extends JpaRepository<BookingFlight, Long> {
}
