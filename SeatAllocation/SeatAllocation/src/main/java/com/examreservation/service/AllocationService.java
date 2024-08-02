package com.examreservation.service;

import com.examreservation.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllocationService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HallService hallService;

    private static List<ReservationDTO> getCollect(List<Reservation> currentReservations) {
        return currentReservations.stream().map(reservation -> new ReservationDTO(reservation.getReservationId(), reservation.getHallId(), reservation.getStudent(), reservation.getSlotNumber(), reservation.getRowNumber(), reservation.getExam(), reservation.getSession(), reservation.getReservationDate())).collect(Collectors.toList());
    }

    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    public List<ReservationDTO> allocateSeats(String subject, String session, String date, int numberOfStudents) {
        List<Hall> halls = hallService.getAllHall();
        List<Reservation> currentReservations = new ArrayList<>();
        int currentStudent = 1;
        boolean alternateSubject = false;

        System.out.println("Starting allocation of seats...");
        System.out.println("Subject: " + subject);
        System.out.println("Session: " + session);
        System.out.println("Date: " + date);
        System.out.println("Number of Students: " + numberOfStudents);

        try {
            for (Hall hall : halls) {
                System.out.println("Processing hall: " + hall.getHallId());
                List<RowEntity> rowEntities = hall.getRowList();
                for (int i = 0; i < rowEntities.size(); ) {
                    RowEntity rowEntity = rowEntities.get(i);
                    System.out.println("Processing row: " + rowEntity.getRowNumber());
                    System.out.println(rowEntity.getSlots().size());
                    for (Slot slot : rowEntity.getSlots()) {
                        boolean two = reservationService.isSlotEligible(hall.getHallId(), session, date, rowEntity.getRowNumber(), slot.getSlotNumber());
                        System.out.println(two);
                        if (two) {
                            Reservation reservation = new Reservation();
                            reservation.setHallId(hall.getHallId());
                            reservation.setStudent(currentStudent);
                            reservation.setSlotNumber(slot.getSlotNumber());
                            reservation.setRowNumber(rowEntity.getRowNumber());
                            reservation.setExam(subject);
                            reservation.setSession(session);
                            reservation.setReservationDate(date);

                            System.out.println("Reserving slot: Hall=" + hall.getHallId() + ", Row=" + rowEntity.getRowNumber() + ", Slot=" + slot.getSlotNumber());

                            reservationService.persistReservation(reservation);
                            currentReservations.add(reservation);
                            alternateSubject = true;
                            System.out.println("Reservation made for student " + currentStudent);
                            currentStudent++;
                            if (currentStudent > numberOfStudents) {
                                System.out.println("All students allocated. Exiting...");
                                return getCollect(currentReservations);
                            }
                        }
                    }
                    i += alternateSubject ? 2 : 1;
                    alternateSubject = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred during seat allocation:");
            e.printStackTrace();
            // Handle exceptions
        }
        return getCollect(currentReservations);
    }


}

