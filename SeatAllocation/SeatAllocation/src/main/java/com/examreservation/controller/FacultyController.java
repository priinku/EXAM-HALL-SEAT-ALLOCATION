package com.examreservation.controller;

import com.examreservation.entity.Faculty;
import com.examreservation.entity.FacultyAssignment;
import com.examreservation.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/add")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.addFaculty(faculty));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, faculty));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") Long id) {
        Optional<Faculty> faculty = facultyService.findById(id);
        return faculty.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        List<Faculty> facultyList = facultyService.findAll();
        if (facultyList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facultyList);
    }

    @PostMapping("/allot")
    public ResponseEntity<?> allotFacultyToHalls(@RequestParam String date, @RequestParam String session) {
        List<FacultyAssignment> facultyAssignments = facultyService.allotFacultyToHalls(date, session);
        System.out.println(facultyAssignments);
        return ResponseEntity.ok(facultyAssignments);
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<FacultyAssignment>> getAssignments(@RequestParam String date, @RequestParam String session) {
        List<FacultyAssignment> assignments = facultyService.getAllAssignments(date, session);
        if (assignments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/schedule")
    public ResponseEntity<?> getSchedule(
            @RequestParam("facultyId") long facultyId,
            @RequestParam("date") String date) {

        try {
            // Fetch schedule from the service layer
            List<FacultyAssignment> assignments = facultyService.getAssignments(date, facultyId);
            System.out.println(assignments);
            if (assignments != null) {
                return ResponseEntity.ok().body(assignments);
            } else {
                return ResponseEntity.status(404).body("No schedule found for the given date and faculty ID.");
            }
        } catch (Exception e) {
            // Log the error and return an appropriate response
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while fetching the schedule.");
        }
    }
}
