package com.pois.pois.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pois.pois.dtos.CreatePointOfInterestDto;
import com.pois.pois.dtos.NearbyPointOfInterestDto;
import com.pois.pois.dtos.PointOfInterestDto;
import com.pois.pois.models.PointOfInterest;
import com.pois.pois.repositories.PointOfInterestRepository;

@Service
public class PointOfInterestService {
    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    public void createPointOfInterest(CreatePointOfInterestDto createPointOfInterestDto) {
        PointOfInterest pointOfInterest = PointOfInterest.builder()
            .name(createPointOfInterestDto.name())
            .coordinateX(createPointOfInterestDto.coordinateX())
            .coordinateY(createPointOfInterestDto.coordinateY())
            .build();
        pointOfInterestRepository.save(pointOfInterest);
    }

    public List<PointOfInterestDto> getAllPointsOfInterest() {
        return pointOfInterestRepository.findAll()
            .stream()
            .map(pointOfInterest -> new PointOfInterestDto(
                pointOfInterest.getName(),
                pointOfInterest.getCoordinateX(),
                pointOfInterest.getCoordinateY()
            ))
            .collect(Collectors.toList());
    }

    public List<NearbyPointOfInterestDto> getNearbyPointsOfInterest(
        Long coordinateX,
        Long coordinateY,
        Long distance
    ) {
        return pointOfInterestRepository.findAll()
            .stream()
            .map(pointOfInterest -> {
                long deltaX = Math.abs(coordinateX - pointOfInterest.getCoordinateX());
                long deltaY = Math.abs(coordinateY - pointOfInterest.getCoordinateY());
                double distanceBetween = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                return new NearbyPointOfInterestDto(
                    pointOfInterest.getName(),
                    pointOfInterest.getCoordinateX(),
                    pointOfInterest.getCoordinateY(),
                    Double.valueOf(distanceBetween).longValue()
                );
            })
            .filter(dto -> dto.distance() <= distance)
            .sorted(Comparator.comparingLong(NearbyPointOfInterestDto::distance))
            .collect(Collectors.toList());
    }

}
