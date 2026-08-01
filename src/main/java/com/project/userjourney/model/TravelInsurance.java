package com.project.userjourney.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "travel_insurance")
public class TravelInsurance {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID insuranceId;
    private UUID customerId;
    private UUID priceId;
    private String plan;
    private String coverage;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;

}
