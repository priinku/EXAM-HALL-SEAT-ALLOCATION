package com.examreservation.service;

import com.examreservation.entity.Hall;
import com.examreservation.entity.HallDTO;
import com.examreservation.entity.Reservation;
import com.examreservation.repository.HallRepository;
import com.examreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<HallDTO> getAllHalls() {
        return hallRepository.findAll().stream().map(hall -> new HallDTO(hall.getHallId(), hall.getHallName(), hall.getRows(), hall.getBenchesPerRow(), hall.getTotalBench())).collect(Collectors.toList());
    }

    public List<Hall> getAllHall() {
        return hallRepository.findAll();
    }

    public Hall addHall(Hall hall) {
        return hallRepository.save(hall);
    }

    public Hall updateHall(Long id, Hall hall) {
        return hallRepository.save(hall);
    }

    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }

    public HallDTO getHallDTOById(long id) {
        Optional<Hall> hallOptional = hallRepository.findById(id);
        return hallOptional.map(hall -> new HallDTO(hall.getHallId(), hall.getHallName(), hall.getRows(), hall.getBenchesPerRow(), hall.getTotalBench())).orElseGet(null);
    }

    public List<Reservation> getReservationsByHallAndDateAndSession(long hallId, String date, String session) {
        return reservationRepository.findByHallIdAndReservationDateAndSession(hallId, date, session);
    }

    public Hall getHallById(long hallId) {
        return hallRepository.findById(hallId).orElseThrow(() -> new RuntimeException("Hall not found"));
    }
}
