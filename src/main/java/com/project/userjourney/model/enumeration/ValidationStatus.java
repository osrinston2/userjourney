package com.project.userjourney.model.enumeration;

import lombok.Getter;

@Getter
public enum ValidationStatus {
    SUCCESS("success"), FAILED("failed");

    private final String value;
    ValidationStatus(String value) {
        this.value = value;
    }
}
