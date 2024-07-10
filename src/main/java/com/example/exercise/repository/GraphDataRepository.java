package com.example.exercise.repository;

import com.example.exercise.model.GraphData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphDataRepository extends JpaRepository<GraphData, Long> {
}
