package com.project.userjourney.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "price")
public class Price {
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID priceId;

    private String plan;

    private String coverage;

    private String area;

    private Double price;

}
