package com.project.userjourney.model.enumeration;

import lombok.Getter;

@Getter
public enum Area {
    AREA_1("AREA 1","Australia, Brunei, Cambodia, China, Hong Kong, India, Indonesia, Japan, Korea, Laos, Macau, Maldives, Myanmar, New Zealand, Pakistan, Philippines, Singapore, Sri Lanka, Taiwan, Thailand and Vietnam"),
    AREA_2("AREA 2", "Europe, Tibet, Nepal, Mongolia, Bhutan and Countries in Area 1"),
    AREA_3("AREA 3", "Worldwide and countries in Area 1 and 2 but excluding Afghanistan, Cuba, Democratic Republic of Congo, Iran, Iraq, Sudan and Syria"),
    AREA_4("AREA 4","Malaysia (single trip between Peninsular and East Malaysia and vice versa)");

    private final String value;
    private final String description;

    Area(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Area fromValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }

        for (Area area : Area.values()) {
            if (area.getValue().equalsIgnoreCase(value.trim())) {
                return area;
            }
        }

        throw new IllegalArgumentException("Invalid area: " + value);
    }

}
