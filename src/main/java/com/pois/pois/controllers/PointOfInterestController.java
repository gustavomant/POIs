package com.pois.pois.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pois.pois.dtos.CreatePointOfInterestDto;
import com.pois.pois.dtos.NearbyPointOfInterestDto;
import com.pois.pois.dtos.PointOfInterestDto;
import com.pois.pois.services.PointOfInterestService;

@RestController
@RequestMapping("/pois")
public class PointOfInterestController {
    @Autowired
    private PointOfInterestService pointOfInterestService;
    
    @PostMapping
    public ResponseEntity<Void> createPointOfInterest(@RequestBody CreatePointOfInterestDto createPointOfInterestDto) {
        pointOfInterestService.createPointOfInterest(createPointOfInterestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    } 

    @GetMapping
    public ResponseEntity<List<PointOfInterestDto>> getAllPointsOfInterest() {
        List<PointOfInterestDto> pointsOfInterest = pointOfInterestService.getAllPointsOfInterest();
        return ResponseEntity.ok(pointsOfInterest);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<NearbyPointOfInterestDto>> getNearbyPointsOfInterest(
        @RequestParam Long coordinateX,
        @RequestParam Long coordinateY,
        @RequestParam Long distance
    ) {
        List<NearbyPointOfInterestDto> nearbyPointOfInterestDto = 
            pointOfInterestService.getNearbyPointsOfInterest(coordinateX, coordinateY, distance);

        return ResponseEntity.ok().body(nearbyPointOfInterestDto);
    }
}
