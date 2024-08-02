package com.examreservation.controller;

import com.examreservation.entity.Hall;
import com.examreservation.entity.HallDTO;
import com.examreservation.entity.HallRequest;
import com.examreservation.entity.Reservation;
import com.examreservation.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/hall")
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping("/viewAllHall")
    public ResponseEntity<List<HallDTO>> getAllHalls() {
        System.out.println(hallService.getAllHalls());
        return ResponseEntity.ok(hallService.getAllHalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDTO> getHallById(@PathVariable long id) {
        HallDTO hallById = hallService.getHallDTOById(id);
        return ResponseEntity.ok(hallById);
    }

    @PostMapping("/addHall")
    public ResponseEntity<Hall> addHall(@RequestBody HallRequest hallRequest) {
        System.out.println(hallRequest.getHallName());
        Hall hall = new Hall(hallRequest.getId(), hallRequest.getHallName(), hallRequest.getRows(), hallRequest.getBenchesPerRow());
        return ResponseEntity.ok(hallService.addHall(hall));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @RequestBody Hall hall) {
        return ResponseEntity.ok(hallService.updateHall(id, hall));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/slots")
    public Map<String, Integer> getSlots(@RequestParam long hallId, @RequestParam String date, @RequestParam String session) {
        System.out.println("Hall "+hallId+" Date :"+date+" session :"+session);
        List<Reservation> reservations = hallService.getReservationsByHallAndDateAndSession(hallId, date, session);
        Hall hall = hallService.getHallById(hallId);

        int reservedSlots = reservations.size();
        int totalSlots = hall.getRows() * hall.getBenchesPerRow();
        int availableSlots = totalSlots - reservedSlots;

        return Map.of(
                "reservedSlots", reservedSlots,
                "availableSlots", availableSlots,
                "totalSlots", totalSlots
        );
    }
}
