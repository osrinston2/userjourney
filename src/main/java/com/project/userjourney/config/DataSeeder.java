package com.project.userjourney.config;

import com.project.userjourney.model.Price;
import com.project.userjourney.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PriceRepository priceRepository;

    public DataSeeder(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(String... args) {

        // Seed only if the table is empty
        if (priceRepository.count() == 0) {

            priceRepository.saveAll(List.of(
                    new Price(null, "PLAN A", "SINGLE TRIP", "AREA 1", 10.00),
                    new Price(null, "PLAN A", "SINGLE TRIP", "AREA 2", 15.00),
                    new Price(null, "PLAN A", "SINGLE TRIP", "AREA 3", 20.00),
                    new Price(null, "PLAN A", "SINGLE TRIP", "AREA 4", 5.00),
                    new Price(null, "PLAN B", "SINGLE TRIP", "AREA 1", 20.00),
                    new Price(null, "PLAN B", "SINGLE TRIP", "AREA 2", 30.00),
                    new Price(null, "PLAN B", "SINGLE TRIP", "AREA 3", 40.00),
                    new Price(null, "PLAN B", "SINGLE TRIP", "AREA 4", 10.00),
                    new Price(null, "PLAN A", "ANNUAL", "AREA 1", 100.00),
                    new Price(null, "PLAN A", "ANNUAL", "AREA 2", 150.00),
                    new Price(null, "PLAN A", "ANNUAL", "AREA 3", 200.00),
                    new Price(null, "PLAN B", "ANNUAL", "AREA 1", 150.00),
                    new Price(null, "PLAN B", "ANNUAL", "AREA 2", 200.00),
                    new Price(null, "PLAN B", "ANNUAL", "AREA 3", 250.00)
            ));

            System.out.println("Price data seeded.");
        }
    }
}
