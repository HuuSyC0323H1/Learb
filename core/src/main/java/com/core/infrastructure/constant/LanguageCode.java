package com.core.infrastructure.constant;

public enum LanguageCode {
    VI("vi"), EN("en");

    private String value;

    private LanguageCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
