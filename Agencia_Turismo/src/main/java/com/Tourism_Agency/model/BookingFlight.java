package com.Tourism_Agency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingFlightId;

    @Column(name = "reserve_date")
    private LocalDate reserveDate;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToMany
    @JoinTable(
            name = "booking_passenger",
            joinColumns = @JoinColumn(name = "booking_flight_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id")
    )
    private List<AppUser> passengers;
}

