package com.examreservation.entity;

import jakarta.persistence.*;

@Entity

public class FacultyAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;

    @ManyToOne
    @JoinColumn(name = "id")
    private Faculty faculty;

    private Long hall;

    private String date;
    private String session;

    // Getters and setters
    public Long getId() {
        return uuid;
    }

    public void setId(Long id) {
        this.uuid = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Long getHall() {
        return hall;
    }

    public void setHall(Long hall) {
        this.hall = hall;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}