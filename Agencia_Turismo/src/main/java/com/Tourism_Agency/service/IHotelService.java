package com.Tourism_Agency.service;

import com.Tourism_Agency.dto.HotelDTO;
import com.Tourism_Agency.model.Hotel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {
    HotelDTO listHotelId(String code);

    List<HotelDTO> listHotelsWithFilters(String city, LocalDate dateFrom, LocalDate dateTo);

    List<Hotel> listHotels();

    void hotelValidation(Hotel hotel);

    void saveHotel(Hotel hotel);

    ResponseEntity<Object> deleteHotel(Long id);

    Hotel editHotel(Hotel hotel);
}
