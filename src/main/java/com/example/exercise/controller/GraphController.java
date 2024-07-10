package com.example.exercise.controller;

import com.example.exercise.model.AreaResult;
import com.example.exercise.model.GraphData;
import com.example.exercise.service.AreaCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GraphController {

    @Autowired
    private AreaCalcService areaCalcService;



    @GetMapping("/graphs/{id}/area") //calculate area
    public ResponseEntity<AreaResult> calculateArea(@PathVariable Long id, @RequestParam int start, @RequestParam int end) {
        try {
            int area = areaCalcService.calculateArea(id, start, end);
            return ResponseEntity.ok(new AreaResult(area));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/graphs") //Create graph data
    public GraphData createGraphData(@RequestBody int[] heights) {
        return areaCalcService.saveGraphData(new GraphData(heights));
    }

    @DeleteMapping("/graphs/{id}") //Delete graph data
    public ResponseEntity<Void> deleteGraphData(@PathVariable Long id) {
        areaCalcService.deleteGraphData(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/graphs") //get all
    public List<GraphData> getAllGraphData() {
        return areaCalcService.getAllGraphData();
    }

    @GetMapping("/graphs/{id}") //Get by id
    public ResponseEntity<GraphData> getGraphDataById(@PathVariable Long id) {
        Optional<GraphData> graphData = areaCalcService.getGraphDataById(id);
        if (graphData.isPresent()) {
            return ResponseEntity.ok(graphData.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/graphs/{id}") //Update data
    public ResponseEntity<GraphData> updateGraphData(@PathVariable Long id, @RequestBody int[] heights) {
        Optional<GraphData> optionalGraphData = areaCalcService.getGraphDataById(id);
        if (optionalGraphData.isPresent()) {
            GraphData graphData = optionalGraphData.get();
            graphData.setHeights(heights);
            GraphData updatedGraphData = areaCalcService.saveGraphData(graphData);
            return ResponseEntity.ok(updatedGraphData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
