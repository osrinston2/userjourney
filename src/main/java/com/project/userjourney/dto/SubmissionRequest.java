package com.project.userjourney.dto;

import com.project.userjourney.model.Customer;
import com.project.userjourney.model.converter.GenderConverter;
import com.project.userjourney.model.enumeration.Gender;
import jakarta.persistence.Convert;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequest {
    @Valid
    @NotNull(message = "Customer details are required")
    private CustomerDetail customer;

    @NotBlank(message = "Coverage cannot be blank")
    private String coverage;

    @NotBlank(message = "Plan cannot be blank")
    private String plan;

    @NotBlank(message = "Area cannot be blank")
    private String area;

    @NotNull(message = "Price ID is required")
    private UUID priceId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    private Double totalPrice;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomerDetail {
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
}
