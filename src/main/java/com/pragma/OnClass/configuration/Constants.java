package com.pragma.OnClass.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "the element indicated does no exist";

    public static  final String INCOMPATIBLE_VALUE_EXCEPTION_MESSAGE = "The value indicated is incompatible with the field";
    public static final String TECHNOLOGY_ALREADY_EXITS_EXCEPTION_MESSAGE = "the technology you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The Field indicated is Empty or Null";

    public static final String MAX_CHAR_EXCEPTION_MESSAGE = "The Field indicated exceed the maximum number of characters";

    public static final String  DUPLICATE_VALUE_EXCEPTION_MESSAGE = "The value indicated is already in use";

    public static final String DUPLICATE_VALUE_CAPACITIES_EXCEPTION = "The values indicated in capacities are duplicated";

    public static final String TECHNOLOGY_NOT_FOUND_EXCEPTION = "the value the technology no exists";




}
