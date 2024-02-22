package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.BookingRoomDTO;
import com.Tourism_Agency.model.BookingRoom;
import com.Tourism_Agency.model.Room;
import com.Tourism_Agency.repository.BookingRoomRepository;
import com.Tourism_Agency.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingRoomService implements IBookingRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IAppUserService clientService;

    private BookingRoomDTO convertToDTO(BookingRoom roomBooking) {
        return modelMapper.map(roomBooking, BookingRoomDTO.class);
    }

    @Override
    public BookingRoomDTO listBookingRoomId(Long id) {
        BookingRoom roomBooking = bookingRoomRepository.findById(id).orElse(null);
        if (roomBooking == null) {
            throw new RuntimeException("Room booking not found for id: " + id);
        }
        return convertToDTO(roomBooking);
    }

    @Override
    public List<BookingRoomDTO> listBookingRooms() {
        List<BookingRoom> roomBookings = bookingRoomRepository.findAll();
        if (roomBookings.isEmpty()) {
            throw new RuntimeException("No room bookings found.");
        }
        return roomBookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String addBookingRoom(BookingRoom roomBooking) {
        Long roomId = roomBooking.getRoom().getCode();

        Room room = roomRepository.findById(String.valueOf(roomId)).orElse(null);
        if (room == null) {
            throw new RuntimeException("Room not found for id: " + roomId);
        }

        List<BookingRoom> roomBookings = bookingRoomRepository.findByRoom(room);

        Double totalPrice = calculateTotalPrice(room, roomBooking);
        bookingRoomRepository.save(roomBooking);
        return "Total price for booking is: " + totalPrice;
    }

    private Double calculateTotalPrice(Room room, BookingRoom roomBooking) {
        Double totalPrice = room.getPriceNight();
        LocalDate roomInDate = roomBooking.getDateFrom();
        LocalDate roomOutDate = roomBooking.getDateTo();
        long days = roomInDate.until(roomOutDate).getDays();
        totalPrice *= days;
        return totalPrice;
    }

    @Override
    public void deleteBookingRoom(Long id) {
        if (bookingRoomRepository.findById(id).isPresent()) {
            bookingRoomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Room booking not found for id: " + id);
        }
    }

    @Override
    public void editBookingRoom(Long id, BookingRoom roomBooking) {
        BookingRoom booking = bookingRoomRepository.findById(roomBooking.getBookingRoomId()).orElse(null);
        if (booking != null) {
            bookingRoomRepository.save(roomBooking);
        } else {
            throw new RuntimeException("Room booking not found for id: " + roomBooking.getBookingRoomId());
        }
    }

}