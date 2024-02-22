package com.Tourism_Agency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Flight {

    @Id
    private String code;

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private String seatType;
    private Double price;
    private Integer seats;

    @JsonIgnore
    @OneToMany(mappedBy = "flight")
    private List<BookingFlight> bookings;
}
