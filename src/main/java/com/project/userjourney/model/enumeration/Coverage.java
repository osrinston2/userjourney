package com.project.userjourney.model.enumeration;

import lombok.Getter;

@Getter
public enum Coverage {
    SINGLE("SINGLE TRIP"),
    ANNUAL("ANNUAL");

    private final String value;

    Coverage(String value) {
        this.value = value;
    }

    public static Coverage fromValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }

        for (Coverage coverage : Coverage.values()) {
            if (coverage.getValue().equalsIgnoreCase(value.trim())) {
                return coverage;
            }
        }

        throw new IllegalArgumentException("Invalid coverage: " + value);
    }
}
