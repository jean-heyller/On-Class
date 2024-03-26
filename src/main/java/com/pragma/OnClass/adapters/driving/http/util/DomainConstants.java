package com.pragma.OnClass.adapters.driving.http.util;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum field{
        NAME,
        DESCRIPTION,
        TECHNOLOGIES,

        CAPACITIES
    }
    public static final String FIELD_NAME_NULL_MESSAGE = "Name cannot be null";
    public static final String FIELD_NAME_SIZE_MESSAGE = "Name cannot be greater than 50 characters";
    public static final String FIELD_DESCRIPTION_SIZE_MESSAGE = "Description cannot be greater than 90 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Description cannot be null";

    public static final String FIELD_CAPACITIES_NULL_MESSAGE = "Capacities cannot be empty";

    public static final String FIELD_CAPACITIES_SIZE_MESSAGE = "Capacities cannot be greater than 4 and less than 1";

    public static final String FIELD_TECHNOLOGIES_EMPTY_MESSAGE = "Technologies cannot be empty";
    public static final String FIELD_TECHNOLOGIES_SIZE_MESSAGE = "Technologies cannot be greater than 20 characters and less than 3 characters";

}
