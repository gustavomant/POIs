package com.pois.pois.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pois.pois.dtos.CreatePointOfInterestDto;
import com.pois.pois.dtos.NearbyPointOfInterestDto;
import com.pois.pois.dtos.PointOfInterestDto;
import com.pois.pois.services.PointOfInterestService;

public class PointOfInterestControllerTest {
    @Mock
    private PointOfInterestService pointOfInterestService;

    @InjectMocks
    private PointOfInterestController pointOfInterestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pointOfInterestController).build();
    }

    @Test
    void testCreatePointOfInterest() throws Exception {
        CreatePointOfInterestDto dto = new CreatePointOfInterestDto("Park", 10L, 20L);
        
        mockMvc.perform(
            post("/pois")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Park\", \"coordinateX\":10, \"coordinateY\":20}"))
            .andExpect(status().isCreated()
        );

        verify(pointOfInterestService, times(1)).createPointOfInterest(dto);
    }

    @Test
    void testGetAllPointsOfInterest() throws Exception {
        List<PointOfInterestDto> points = Arrays.asList(
                new PointOfInterestDto("Park", 10L, 20L),
                new PointOfInterestDto("Museum", 30L, 40L)
        );

        when(pointOfInterestService.getAllPointsOfInterest()).thenReturn(points);

        mockMvc.perform(get("/pois"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Park"))
                .andExpect(jsonPath("$[1].name").value("Museum"));

        verify(pointOfInterestService, times(1)).getAllPointsOfInterest();
    }

    @Test
    void testGetNearbyPointsOfInterest() throws Exception {
        List<NearbyPointOfInterestDto> nearbyPoints = Arrays.asList(
                new NearbyPointOfInterestDto("Park", 10L, 20L, 5L),
                new NearbyPointOfInterestDto("Museum", 30L, 40L, 10L)
        );

        when(pointOfInterestService.getNearbyPointsOfInterest(10L, 20L, 15L)).thenReturn(nearbyPoints);

        mockMvc.perform(get("/pois/nearby")
                .param("coordinateX", "10")
                .param("coordinateY", "20")
                .param("distance", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Park"))
                .andExpect(jsonPath("$[1].name").value("Museum"));

        verify(pointOfInterestService, times(1)).getNearbyPointsOfInterest(10L, 20L, 15L);
    }
}
