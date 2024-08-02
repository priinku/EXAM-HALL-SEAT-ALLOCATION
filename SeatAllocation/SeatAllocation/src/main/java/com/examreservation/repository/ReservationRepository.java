package com.examreservation.repository;

import com.examreservation.entity.Hall;
import com.examreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findByHallIdAndSessionAndReservationDateAndRowNumberAndSlotNumber(long hallId, String session, String date, int rowNumber, int slotNumber);
    List<Reservation> findByHallIdAndReservationDateAndSession(long hallId, String reservationDate, String session);
    @Query(value = "SELECT * FROM reservation WHERE exam = :exam AND reservation_date = :reservation_date AND session = :session", nativeQuery = true)
    List<Reservation> findReservations(@Param("exam") String exam,@Param("reservation_date") String reservation_date,@Param("session") String session);

    @Query("SELECT DISTINCT r.hallId FROM Reservation r WHERE r.reservationDate = :reservation_date AND r.session = :session")
    List<Long> findBookedHallsByDateAndSession(@Param("reservation_date") String reservation_date, @Param("session") String session);

}


