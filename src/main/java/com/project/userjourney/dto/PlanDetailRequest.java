package com.project.userjourney.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanDetailRequest {
    private String coverage;

    private String area;

    @Valid
    private Dates dates;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Dates {
        private LocalDate startDate;

        private LocalDate endDate;
    }
}
