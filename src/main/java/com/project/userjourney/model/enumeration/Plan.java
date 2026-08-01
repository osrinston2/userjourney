package com.project.userjourney.model.enumeration;

public enum Plan {
    PLAN_A("PLAN A"),
    PLAN_B("PLAN B");

    private final String planName;
    Plan(String planName) {
        this.planName = planName;
    }

    public String getPlanName() {
        return planName;
    }
}
