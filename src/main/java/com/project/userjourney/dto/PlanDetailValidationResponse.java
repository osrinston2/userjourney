package com.project.userjourney.dto;

import lombok.Data;

@Data
public class PlanDetailValidationResponse {
    private String status;
    private CriteriaObject criteria;

    @Data
    public static class CriteriaObject {
        private String coverage = "";
        private String area = "";
        private Dates dates;
    }

    @Data
    public static class Dates {
        private String startDate = "";
        private String endDate = "";
    }
}
