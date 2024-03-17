package com.pragma.OnClass.adapters.driving.http.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum field{
        NAME,
        DESCRIPTION
    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Name cannot be null";
    public static final String FIELD_NAME_SIZE_MESSAGE = "Name cannot be greater than 50 characters";
    public static final String FIELD_DESCRIPTION_SIZE_MESSAGE = "Description cannot be greater than 90 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Description cannot be null";

}
