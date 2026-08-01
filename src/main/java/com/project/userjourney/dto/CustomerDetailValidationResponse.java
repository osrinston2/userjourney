package com.project.userjourney.dto;

import lombok.Data;

@Data
public class CustomerDetailValidationResponse {
    private String status;
    private CustomerDetailValidationResponse.CriteriaObject criteria;

    @Data
    public static class CriteriaObject {
        private String fullName = "";
        private String nric = "";
        private String email = "";
        private String mobileNo = "";
        private String addressLine1 = "";
        private String postCode = "";
    }
}
