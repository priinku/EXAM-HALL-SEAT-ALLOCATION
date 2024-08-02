package com.examreservation.entity;

public class HallDTO {
    private Long hallId;
    private String hallName;
    private int rows;
    private int benchesPerRow;
    private int totalBench;

    public HallDTO(Long hallId, String hallName, int rows, int benchesPerRow, int totalBench) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.rows = rows;
        this.benchesPerRow = benchesPerRow;
        this.totalBench = totalBench;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getBenchesPerRow() {
        return benchesPerRow;
    }

    public void setBenchesPerRow(int benchesPerRow) {
        this.benchesPerRow = benchesPerRow;
    }

    public int getTotalBench() {
        return totalBench;
    }

    public void setTotalBench(int totalBench) {
        this.totalBench = totalBench;
    }
// Constructor, getters, and setters

}
