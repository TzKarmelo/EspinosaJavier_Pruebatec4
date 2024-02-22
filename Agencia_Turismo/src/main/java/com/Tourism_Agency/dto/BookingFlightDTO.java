package com.Tourism_Agency.dto;

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
public class BookingFlightDTO {

    private Long id;
    private FlightDTO flightDTO;
    private List<AppUserDTO> usersDTO;
}
