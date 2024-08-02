package com.examreservation.service;

import com.examreservation.entity.Faculty;
import com.examreservation.entity.FacultyAssignment;
import com.examreservation.entity.Hall;
import com.examreservation.repository.FacultyAssignmentRepository;
import com.examreservation.repository.FacultyRepository;
import com.examreservation.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private FacultyAssignmentRepository facultyAssignmentRepository;

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Optional<Faculty> findById(Long id) {
        return facultyRepository.findById(id);
    }

    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }


    public List<FacultyAssignment> allotFacultyToHalls(String date, String session) {
        List<Long> bookedHalls = reservationService.getBookedHalls(date, session);
        List<Faculty> faculties = findAll();
        Collections.shuffle(faculties);
        List<FacultyAssignment> facultyAssignments = new ArrayList<>();
        for (int i = 0; i < bookedHalls.size(); i++) {
            long hall_id = bookedHalls.get(i);
            if (i < faculties.size()) {
                Faculty faculty = faculties.get(i);
                FacultyAssignment assignment = new FacultyAssignment();
                assignment.setFaculty(faculty);
                assignment.setHall(hall_id);
                assignment.setDate(date);
                assignment.setSession(session);
                facultyAssignments.add(assignment);
            } else {
                break; // Not enough faculties to allot
            }
        }
        facultyAssignmentRepository.saveAll(facultyAssignments);
        return facultyAssignments;
    }

    public List<FacultyAssignment> getAllAssignments(String date, String session) {
        return facultyAssignmentRepository.findByDateAndSession(date, session);
    }
    public List<FacultyAssignment> getAssignments(String date, long facultyId) {
        return facultyAssignmentRepository.findByDateAndFaculty_Id(date, facultyId);
    }
}
