package com.Tourism_Agency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRoomDTO {

    private String dateFrom;
    private String dateTo;
    private RoomDTO room;
    private List<AppUserDTO> clients;
}
