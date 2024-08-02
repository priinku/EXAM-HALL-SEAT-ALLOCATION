package com.examreservation.repository;

import com.examreservation.entity.FacultyAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyAssignmentRepository extends JpaRepository<FacultyAssignment, Long> {
    List<FacultyAssignment> findByDateAndSession(String date, String session);
    List<FacultyAssignment> findByDateAndFaculty_Id(String date, Long facultyId);

}
