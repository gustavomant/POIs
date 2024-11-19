package com.pois.pois.dtos;

public record NearbyPointOfInterestDto (
    String name,
    Long coordinateX,
    Long coordinateY,
    Long distance
) {}
