package com.examreservation.entity;

public class ReservationDTO {
    private int reservationId;
    private long hallId;
    private int student;
    private int slotNumber;
    private int rowNumber;
    private String exam;
    private String session;
    private String reservationDate;

    // Constructor
    public ReservationDTO(int reservationId, long hallId, int student, int slotNumber, int rowNumber, String exam, String session, String reservationDate) {
        this.reservationId = reservationId;
        this.hallId = hallId;
        this.student = student;
        this.slotNumber = slotNumber;
        this.rowNumber = rowNumber;
        this.exam = exam;
        this.session = session;
        this.reservationDate = reservationDate;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
