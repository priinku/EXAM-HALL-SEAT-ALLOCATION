package com.examreservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hall_slot")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "slot_number")
    private int slotNumber;

    @ManyToOne
    @JoinColumn(name = "row_num")
    private RowEntity rowNumber;

    public Slot() {
        // No-argument constructor for JPA
    }

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public RowEntity getRow() {
        return rowNumber;
    }

    public void setRow(RowEntity row) {
        System.out.println(row.getRowNumber());
        this.rowNumber = row;
    }
}
