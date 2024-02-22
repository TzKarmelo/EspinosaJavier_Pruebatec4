package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.RoomDTO;
import com.Tourism_Agency.model.Hotel;
import com.Tourism_Agency.model.Room;
import com.Tourism_Agency.repository.HotelRepository;
import com.Tourism_Agency.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    private RoomDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public RoomDTO listRoomId(Long code) {
        Room room = roomRepository.findById(String.valueOf(code)).orElse(null);
        if (room == null) {
            throw new RuntimeException("Room not found for number: " + code);
        }
        return convertToDTO(room);
    }

    @Override
    public List<RoomDTO> listRoomsByHotel(String code) {
        Hotel hotel = hotelRepository.findById(code).orElse(null);
        if (hotel == null) {
            throw new RuntimeException("Hotel not found for code: " + code);
        }
        List<RoomDTO> roomDtos = hotel.getRoomList().stream().map(this::convertToDTO).collect(Collectors.toList());
        return roomDtos;
    }

    @Override
    public Room addRoom(Room room, String code) {
        Hotel hotel = hotelRepository.findById(code).orElse(null);
        if (hotel == null) {
            throw new RuntimeException("Hotel not found for code: " + code);
        }
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    @Override
    public void editRoom(Room room) {
        if (roomRepository.findById(String.valueOf(room.getCode())).isPresent()) {
            roomRepository.save(room);
        } else {
            throw new RuntimeException("Room not found for number: " + room.getCode());
        }
    }

    @Override
    public void deleteRoom(Long code) {
        if (roomRepository.findById(String.valueOf(code)).isPresent()) {
            roomRepository.deleteById(String.valueOf(code));
        } else {
            throw new RuntimeException("Room not found for number: " + code);
        }
    }
}
