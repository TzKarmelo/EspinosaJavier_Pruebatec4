package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.BookingRoomDTO;
import com.Tourism_Agency.model.BookingRoom;

import java.util.List;

public interface IBookingRoomService {
    BookingRoomDTO listBookingRoomId(Long id);

    List<BookingRoomDTO> listBookingRooms();

    String addBookingRoom(BookingRoom roomBooking);

    void deleteBookingRoom(Long id);

    void editBookingRoom(Long id, BookingRoom roomBooking);
}
