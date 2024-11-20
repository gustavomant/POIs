package com.pois.pois.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pois.pois.models.PointOfInterest;

@DataJpaTest
public class PointOfInterestRepositoryTest {
    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @Test
    void testFindByName() {
        PointOfInterest poi = PointOfInterest.builder()
            .name("Park")
            .coordinateX(10L)
            .coordinateY(20L)
            .build();
        
        pointOfInterestRepository.save(poi);

        PointOfInterest foundPoi = pointOfInterestRepository.findByName(poi.getName());

        assertNotNull(foundPoi);
        assertEquals(poi.getName(), foundPoi.getName());
        assertEquals(poi.getCoordinateX(), foundPoi.getCoordinateX());
        assertEquals(poi.getCoordinateY(), foundPoi.getCoordinateY());
    }

    @Test
    void testFindByNameReturnsNullIfNotFound() {
        PointOfInterest foundPoi = pointOfInterestRepository.findByName("Nonexistent");
        assertNull(foundPoi);
    }
}
