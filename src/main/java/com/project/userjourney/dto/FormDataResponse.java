package com.project.userjourney.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FormDataResponse {
    private List<AreaOfTravel> area;
    private List<Coverage> coverage;

    @Data
    public static class AreaOfTravel {
        private String code;
        private String name;
        private String description;

    }

    @Data
    public static class Coverage {
        private String code;
        private String name;
    }
}
