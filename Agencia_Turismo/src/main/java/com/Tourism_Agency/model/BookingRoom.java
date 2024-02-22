package com.Tourism_Agency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingRoomId;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "code")
    private Room room;

    @ManyToMany
    @JoinTable(name = "bookinghotel_appuser",
            joinColumns = @JoinColumn(name = "bookingHotel_id"),
            inverseJoinColumns = @JoinColumn(name = "appUser_id"))
    private List<AppUser> hosts;
}