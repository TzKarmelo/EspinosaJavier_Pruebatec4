package com.Tourism_Agency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private Double totalPrice;
    private String flightCode;
    private String destination;
    private String origin;
    private LocalDate departureDate;
    private String seatType;
}
