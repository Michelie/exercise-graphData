package com.example.exercise.service;

import com.example.exercise.model.GraphData;
import com.example.exercise.repository.GraphDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaCalcService {
    @Autowired
    private GraphDataRepository graphDataRepository;

    //calc area
    public int calculateArea(Long graphId, int start, int end) {
        Optional<GraphData> graphData = graphDataRepository.findById(graphId);
        if (graphData.isPresent()) {
            int[] heights = graphData.get().getHeights();
            int width = (end - start);
            int height = Math.min(heights[start], heights[end]);
            return height * width;
        }
        throw new RuntimeException("Graph data not found");

        //the method retrieves the graph data from db
        // then extracts the heights from the graph obj into array
        //calcs width - difference between end and start indexes
        //calcs height - it's the shortest of the 2 bars
        //calcs the area width * height
    }


    //save data
    public GraphData saveGraphData(GraphData graphData) {
        return graphDataRepository.save(graphData);
    }

    //delete data
    public void deleteGraphData(Long id) {
        graphDataRepository.deleteById(id);
    }

    //get all data
    public List<GraphData> getAllGraphData() {
        return graphDataRepository.findAll();
    }

    //get data by id
    public Optional<GraphData> getGraphDataById(Long id) {
        return graphDataRepository.findById(id);
    }
}
