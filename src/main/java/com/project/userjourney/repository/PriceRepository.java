package com.project.userjourney.repository;

import com.project.userjourney.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
    Price findByPriceId(UUID priceId);

    List<Price> findByAreaAndCoverage(String area, String coverage);
}
