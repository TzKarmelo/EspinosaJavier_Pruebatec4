package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.HotelDTO;
import com.Tourism_Agency.model.BookingRoom;
import com.Tourism_Agency.model.Hotel;
import com.Tourism_Agency.model.Room;
import com.Tourism_Agency.repository.BookingRoomRepository;
import com.Tourism_Agency.repository.HotelRepository;
import com.Tourism_Agency.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HotelService implements IHotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRoomRepository roomBookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    private HotelDTO convertToDTO(Hotel hotel) {
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO listHotelId(String code) {
        Hotel hotel = hotelRepository.findById(code).orElse(null);
        if (hotel == null) {
            throw new RuntimeException("Hotel not found for code: " + code);
        }
        return convertToDTO(hotel);
    }
    @Override
    public List<HotelDTO> listHotelsWithFilters(String city, LocalDate dateFrom, LocalDate dateTo) {
        List<Hotel> hotels;
        if (city != null) {
            hotels = hotelRepository.findByCity(city);
        } else {
            hotels = hotelRepository.findAll();
        }
        if (hotels.isEmpty()) {
            throw new RuntimeException("No hotels found for city: " + city);
        }

        if (dateFrom != null && dateTo != null) {
            LocalDate currentDate = dateFrom;
            while (!currentDate.isAfter(dateTo)) {
                LocalDate finalCurrentDate = currentDate;
                hotels.forEach(hotel -> hotel.getRoomList().forEach(room -> {
                    List<BookingRoom> bookings = roomBookingRepository.findByRoomAndDateFromLessThanEqualAndDateToGreaterThanEqual(room, finalCurrentDate, finalCurrentDate);
                    room.setBookings(bookings);
                }));
                currentDate = currentDate.plusDays(1);
            }

            hotels.forEach(hotel -> hotel.setRoomList(
                    hotel.getRoomList().stream()
                            .filter(room -> room.getBookings().isEmpty())
                            .collect(Collectors.toList())
            ));

            if (hotels.stream().allMatch(hotel -> hotel.getRoomList().isEmpty())) {
                throw new RuntimeException("No rooms available for the selected dates.");
            }

        }

        return hotels.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<Hotel> listHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        if (hotels.isEmpty()) {
            throw new RuntimeException("No hotels found");
        }
        return hotels;
    }

    @Override
    public void hotelValidation(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel not found.");
        }

        if (hotel.getCity() == null || hotel.getCity().isEmpty()) {
            throw new IllegalArgumentException("Hotel name is required.");
        }

        if (hotel.getName() == null || hotel.getName().isEmpty()) {
            throw new IllegalArgumentException("Hotel city is required.");
        }

    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelValidation(hotel);
        Long hotelId = hotel.getHotelId();
        if (hotelId != null && hotelRepository.existsById(String.valueOf(hotelId))) {
            throw new IllegalArgumentException("Hotel with id: " + hotelId + " already exists.");
        }
        hotelRepository.save(hotel);
    }

    @Override
    public ResponseEntity<Object> deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(String.valueOf(id)).orElse(null);
        if (hotel == null) {
            return ResponseEntity.status(400).body("Hotel not found for id: " + id);
        }
        for (Room room : hotel.getRoomList()) {
            if (!room.getBookings().isEmpty()) {
                return ResponseEntity.status(400).body("Hotel with id: " + id + " has bookings.");
            }
        }
        try {
            hotelRepository.deleteById(String.valueOf(id));
            return ResponseEntity.status(200).body("Hotel deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error deleting hotel with id: " + id);
        }
    }

    @Override
    public Hotel editHotel(Hotel hotel) {
        Hotel existingHotel = hotelRepository.findById(String.valueOf(hotel.getHotelId())).orElse(null);
        hotelValidation(existingHotel);
        return hotelRepository.save(hotel);
    }
}
