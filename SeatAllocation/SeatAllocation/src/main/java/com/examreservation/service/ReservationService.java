package com.examreservation.service;

import com.examreservation.entity.Hall;
import com.examreservation.entity.Reservation;
import com.examreservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean isSlotEligible(long hallId, String session, String date, int rowNumber, int slotNumber) {
        return reservationRepository.findByHallIdAndSessionAndReservationDateAndRowNumberAndSlotNumber(hallId, session, date, rowNumber, slotNumber) == null;
    }

    public void persistReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservedSlots(String exam, String session, String reservation_date) {
        // Implement the logic to retrieve reserved slots from the database
        return reservationRepository.findReservations(exam,reservation_date,session);
    }
    public List<Long> getBookedHalls(String date, String session) {
        return reservationRepository.findBookedHallsByDateAndSession(date, session);
    }
}

