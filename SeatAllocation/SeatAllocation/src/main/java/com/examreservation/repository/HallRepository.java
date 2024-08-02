package com.examreservation.repository;

import com.examreservation.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

}
