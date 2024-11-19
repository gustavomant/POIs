package com.pois.pois.dtos;

public record PointOfInterestDto (
    String name,
    Long coordinateX,
    Long coordinateY
) {}
