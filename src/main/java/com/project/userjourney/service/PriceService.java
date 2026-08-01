package com.project.userjourney.service;

import com.project.userjourney.model.Price;
import com.project.userjourney.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> findByAreaAndCoverage(String area, String coverage) {
        return priceRepository.findByAreaAndCoverage(area, coverage);
    }
}
