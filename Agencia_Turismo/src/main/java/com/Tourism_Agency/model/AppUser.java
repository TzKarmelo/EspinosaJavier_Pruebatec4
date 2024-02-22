package com.Tourism_Agency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    private String dni;

    private String name;
    private String lastName;

    @JsonIgnore
    @ManyToMany(mappedBy = "passengers")
    private List<BookingFlight> bookedFlights;

    @JsonIgnore
    @ManyToMany(mappedBy = "hosts")
    private List<BookingRoom> bookedHotels;
}
