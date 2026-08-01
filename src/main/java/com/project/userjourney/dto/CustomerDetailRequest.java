package com.project.userjourney.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDetailRequest {
    private String fullName;

    private String nric;

    private String email;

    private String mobileNo;

    private String addressLine1;

    private String postCode;
}
