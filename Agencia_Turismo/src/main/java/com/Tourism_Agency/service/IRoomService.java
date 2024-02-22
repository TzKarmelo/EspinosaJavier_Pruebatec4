package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.RoomDTO;
import com.Tourism_Agency.model.Room;

import java.util.List;

public interface IRoomService {
    RoomDTO listRoomId(Long code);

    List<RoomDTO> listRoomsByHotel(String code);

    Room addRoom(Room room, String code);

    void editRoom(Room room);

    void deleteRoom(Long code);
}
