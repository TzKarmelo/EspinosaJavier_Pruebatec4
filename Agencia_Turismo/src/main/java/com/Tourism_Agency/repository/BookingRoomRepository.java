package com.Tourism_Agency.repository;

import com.Tourism_Agency.model.BookingRoom;
import com.Tourism_Agency.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {
    List<BookingRoom> findByRoomAndDateFromLessThanEqualAndDateToGreaterThanEqual(Room room, LocalDate dateFrom, LocalDate dateTo);
    List<BookingRoom> findByRoom(Room room);
}
