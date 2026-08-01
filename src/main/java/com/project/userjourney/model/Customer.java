package com.project.userjourney.model;

import com.project.userjourney.model.converter.GenderConverter;
import com.project.userjourney.model.enumeration.Gender;
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
@Table(name = "customer")
public class Customer {
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID customerId;

    private String fullName;
    private String email;
    private String nric;
    private String dateOfBirth;

    @Convert(converter = GenderConverter.class)
    private Gender gender;
    private String mobileNo;
    private String addressLine1;
    private String addressLine2;
    private String postCode;
}
