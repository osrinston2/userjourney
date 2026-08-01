package com.project.userjourney.service;

import com.project.userjourney.exception.ResourceNotFoundException;
import com.project.userjourney.model.Price;
import com.project.userjourney.model.TravelInsurance;
import com.project.userjourney.model.enumeration.Coverage;
import com.project.userjourney.repository.PriceRepository;
import com.project.userjourney.repository.TravelInsuranceRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
public class TravelInsuranceService {
    private final TravelInsuranceRepository travelInsuranceRepository;
    private final PriceRepository priceRepository;

    public TravelInsuranceService(TravelInsuranceRepository travelInsuranceRepository, PriceRepository priceRepository) {
        this.travelInsuranceRepository = travelInsuranceRepository;
        this.priceRepository = priceRepository;
    }

    public TravelInsurance submit(TravelInsurance travelInsurance) {
        Price price = priceRepository.findByPriceId(travelInsurance.getPriceId());
        if (price == null) {
            throw new ResourceNotFoundException("Price record not found for priceId: " + travelInsurance.getPriceId());
        }

        Coverage selectedCoverage = Coverage.fromValue(travelInsurance.getCoverage().toUpperCase());
        if (selectedCoverage == Coverage.SINGLE) {
            long totalDay = ChronoUnit.DAYS.between(travelInsurance.getStartDate(), travelInsurance.getEndDate()) + 1;
            Double calculatedPrice = price.getPrice() * totalDay;

            if (!Objects.equals(calculatedPrice, travelInsurance.getTotalPrice())) {
                throw new IllegalStateException("Invalid total price");
            }
        } else {
            if (!Objects.equals(price.getPrice(), travelInsurance.getTotalPrice())) {
                throw new IllegalStateException("Invalid total price");
            }
        }

        return travelInsuranceRepository.save(travelInsurance);
    }
}
