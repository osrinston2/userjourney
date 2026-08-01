package com.project.userjourney.repository;

import com.project.userjourney.model.TravelInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TravelInsuranceRepository extends JpaRepository<TravelInsurance, UUID> {
    TravelInsurance findByInsuranceId(UUID insuranceId);
}
