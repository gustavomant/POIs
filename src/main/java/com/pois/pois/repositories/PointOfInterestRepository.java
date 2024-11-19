package com.pois.pois.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pois.pois.models.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {
    PointOfInterest findByName(String name);
}
