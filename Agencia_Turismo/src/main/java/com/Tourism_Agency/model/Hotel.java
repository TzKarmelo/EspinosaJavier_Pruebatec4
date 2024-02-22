package com.Tourism_Agency.model;

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
public class Hotel {

    @Id
    private Long hotelId;

    private String name;
    private String city;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> roomList;
}