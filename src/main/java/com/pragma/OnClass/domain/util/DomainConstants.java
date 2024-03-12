package com.pragma.OnClass.domain.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
    public enum Field {
        NAME,
        DESCRIPTION

    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_MESSAGE = "Field 'description' cannot be null";
}
