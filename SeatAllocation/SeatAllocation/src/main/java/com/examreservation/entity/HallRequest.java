package com.examreservation.entity;

public class HallRequest {
    private Long id;
    private String hallName;
    private int rows;
    private int benchesPerRow;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getHallName() {
        return hallName;
    }

    public int getRows() {
        return rows;
    }

    public int getBenchesPerRow() {
        return benchesPerRow;
    }
}
