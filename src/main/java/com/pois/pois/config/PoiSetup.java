package com.pois.pois.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pois.pois.models.PointOfInterest;
import com.pois.pois.repositories.PointOfInterestRepository;

@Configuration
public class PoiSetup {
    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;
    
    @Bean
    CommandLineRunner initPois() {
        return args -> {
            if(pointOfInterestRepository.findByName("Lanchonete") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Lanchonete")
                    .coordinateX(Long.valueOf(27))
                    .coordinateY(Long.valueOf(12))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }

            if(pointOfInterestRepository.findByName("Posto") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Posto")
                    .coordinateX(Long.valueOf(31))
                    .coordinateY(Long.valueOf(18))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }

            if(pointOfInterestRepository.findByName("Joalheria") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Joalheria")
                    .coordinateX(Long.valueOf(15))
                    .coordinateY(Long.valueOf(12))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }

            if(pointOfInterestRepository.findByName("Floricultura") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Floricultura")
                    .coordinateX(Long.valueOf(19))
                    .coordinateY(Long.valueOf(21))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }

            if(pointOfInterestRepository.findByName("Pub") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Pub")
                    .coordinateX(Long.valueOf(12))
                    .coordinateY(Long.valueOf(8))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }

            if(pointOfInterestRepository.findByName("Supermercado") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Supermercado")
                    .coordinateX(Long.valueOf(23))
                    .coordinateY(Long.valueOf(6))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }

            if(pointOfInterestRepository.findByName("Churrascaria") == null) {
                PointOfInterest pointOfInterest = PointOfInterest.builder()
                    .name("Churrascaria")
                    .coordinateX(Long.valueOf(28))
                    .coordinateY(Long.valueOf(2))
                    .build();
                pointOfInterestRepository.save(pointOfInterest);
            }
        };
    }
}