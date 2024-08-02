package com.examreservation.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "hall")
public class Hall {

    @Id
    private long hallId;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name = "total_bench")
    private int totalBench;

    @Column(name = "rows_count")
    private int rows;

    @Column(name = "benches_per_row")
    private int benchesPerRow;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RowEntity> rowList = new ArrayList<>();

    public Hall() {
        // No-argument constructor for JPA
    }

    public Hall(long id ,String hallName, int rows, int benchesPerRow) {
        this.hallId = id;
        this.hallName = hallName;
        this.rows = rows;
        this.benchesPerRow = benchesPerRow;
        this.totalBench = rows * benchesPerRow;

        for (int i = 1; i <= rows; i++) {
            List<Slot> slots = new ArrayList<>();
            for (int j = 1; j <= benchesPerRow; j++) {
                slots.add(new Slot(j));
            }
            RowEntity row = new RowEntity(i, slots, this);
            this.rowList.add(row);
        }
    }

    // Getters and setters

    public long getHallId() {
        return hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public int getTotalBench() {
        return totalBench;
    }

    public int getRows() {
        return rows;
    }

    public int getBenchesPerRow() {
        return benchesPerRow;
    }

    public List<RowEntity> getRowList() {
        return rowList;
    }
}

