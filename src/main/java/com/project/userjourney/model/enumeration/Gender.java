package com.project.userjourney.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("M"), FEMALE("F");

    private final String value;

    Gender(String g) {
        this.value = g;
    }

    @JsonCreator
    public static Gender fromString(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(value)) {
                return gender;
            }
        }

        throw new IllegalArgumentException("Invalid gender: " + value);
    }
}
