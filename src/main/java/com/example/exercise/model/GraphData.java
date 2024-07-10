package com.example.exercise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GraphData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int[] heights;

    public GraphData() {
    }

    public GraphData(int[] heights) {
        this.heights = heights;
    }

    public int[] getHeights() {
        return heights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHeights(int[] heights) {
        this.heights = heights;
    }
}
