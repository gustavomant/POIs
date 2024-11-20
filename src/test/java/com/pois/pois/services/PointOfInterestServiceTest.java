package com.pois.pois.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pois.pois.dtos.CreatePointOfInterestDto;
import com.pois.pois.dtos.NearbyPointOfInterestDto;
import com.pois.pois.dtos.PointOfInterestDto;
import com.pois.pois.models.PointOfInterest;
import com.pois.pois.repositories.PointOfInterestRepository;
import org.mockito.ArgumentCaptor;

public class PointOfInterestServiceTest {
    @Mock
    private PointOfInterestRepository pointOfInterestRepository;

    @InjectMocks
    private PointOfInterestService pointOfInterestService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePointOfInterest() {
        CreatePointOfInterestDto dto = new CreatePointOfInterestDto("Park", 10L, 20L);

        pointOfInterestService.createPointOfInterest(dto);

        ArgumentCaptor<PointOfInterest> captor = ArgumentCaptor.forClass(PointOfInterest.class);
        verify(pointOfInterestRepository, times(1)).save(captor.capture());

        PointOfInterest captured = captor.getValue();
        assertEquals("Park", captured.getName());
        assertEquals(10L, captured.getCoordinateX());
        assertEquals(20L, captured.getCoordinateY());
    }


    @Test
    void testGetAllPointsOfInterest() {
        List<PointOfInterest> points = List.of(
            PointOfInterest.builder().name("Park").coordinateX(10L).coordinateY(20L).build(),
            PointOfInterest.builder().name("Museum").coordinateX(15L).coordinateY(25L).build()
        );

        when(pointOfInterestRepository.findAll()).thenReturn(points);

        List<PointOfInterestDto> result = pointOfInterestService.getAllPointsOfInterest();

        assertEquals(2, result.size());
        assertEquals("Park", result.get(0).name());
        assertEquals(10L, result.get(0).coordinateX());
        assertEquals(20L, result.get(0).coordinateY());
    }

    @Test
    void testGetNearbyPointsOfInterest() {
        List<PointOfInterest> points = List.of(
            PointOfInterest.builder().name("Park").coordinateX(10L).coordinateY(10L).build(),
            PointOfInterest.builder().name("Museum").coordinateX(15L).coordinateY(15L).build(),
            PointOfInterest.builder().name("Cafe").coordinateX(30L).coordinateY(30L).build()
        );

        when(pointOfInterestRepository.findAll()).thenReturn(points);

        Long coordinateX = 10L;
        Long coordinateY = 10L;
        Long distance = 10L;

        List<NearbyPointOfInterestDto> result = pointOfInterestService.getNearbyPointsOfInterest(coordinateX, coordinateY, distance);

        assertEquals(2, result.size());
        assertEquals("Park", result.get(0).name());
        assertEquals(0L, result.get(0).distance());
        assertEquals("Museum", result.get(1).name());
    }
}