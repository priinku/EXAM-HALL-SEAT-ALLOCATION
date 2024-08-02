package com.examreservation.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hall_row")
public class RowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "row_num")
    private int rowNumber;

    @OneToMany(mappedBy = "rowNumber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Slot> slots;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public RowEntity() {
        // No-argument constructor for JPA
    }

    public RowEntity(int rowNumber, List<Slot> slots, Hall hall) {
        this.rowNumber = rowNumber;
        this.slots = slots;
        this.hall = hall;
        for (Slot slot : slots) {
            slot.setRow(this);
        }
    }

    // Getters and setters

    public int getRowNumber() {
        return rowNumber;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public Hall getHall() {
        return hall;
    }
}
