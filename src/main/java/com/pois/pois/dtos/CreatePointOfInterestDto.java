package com.pois.pois.dtos;

public record CreatePointOfInterestDto (
    String name,
    Long coordinateX,
    Long coordinateY
) {}
