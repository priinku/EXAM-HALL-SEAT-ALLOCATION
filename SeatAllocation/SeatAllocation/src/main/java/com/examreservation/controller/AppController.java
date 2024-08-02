package com.examreservation.controller;

import com.examreservation.entity.Reservation;
import com.examreservation.entity.ReservationDTO;
import com.examreservation.service.AllocationService;
import com.examreservation.service.HallService;
import com.examreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AllocationService allocationService;
    @Autowired
    private HallService hallService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/allocate")
    public ResponseEntity<?> showAllocationForm() {
        return ResponseEntity.ok(hallService.getAllHalls());
    }

    @PostMapping("/allocate")
    public ResponseEntity<?> allocateSeats(@RequestParam String subject, @RequestParam String session, @RequestParam String date, @RequestParam int numberOfStudents) throws SQLException {
        List<ReservationDTO> reservations = allocationService.allocateSeats(subject, session, date, numberOfStudents);
        return ResponseEntity.ok(reservations);
    }

    // Method to view reserved slots
    @GetMapping("/viewSlots")
    public ResponseEntity<List<Reservation>> viewReservedSlots(@RequestParam String exam, @RequestParam String session, @RequestParam String reservation_date) {
        try {
            // Call service to get reserved slots
            List<Reservation> reservations = reservationService.getReservedSlots(exam, session, reservation_date);
            System.out.println(reservations.size());
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Adjust based on your error handling strategy
        }
    }
}