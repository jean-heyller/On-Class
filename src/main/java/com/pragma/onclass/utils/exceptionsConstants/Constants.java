package com.pragma.onclass.utils.exceptionsConstants;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "the element indicated does no exist";

    public static  final String INCOMPATIBLE_VALUE_EXCEPTION_MESSAGE = "The value indicated is incompatible with the field";
    public static final String TECHNOLOGY_ALREADY_EXITS_EXCEPTION_MESSAGE = " you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The Field indicated is Empty or Null";

    public static final String MAX_CHAR_EXCEPTION_MESSAGE = "The Field indicated exceed the maximum number of characters";

    public static final String  DUPLICATE_VALUE_EXCEPTION_MESSAGE = "The value indicated is already in use";

    public static final String CAPACITY_ALREADY_EXISTS_EXCEPTION = "The capacity you want to create already exists";

    public static final String BOOTCAMP_ALREADY_EXISTS_EXCEPTION = "The bootcamp you want to create already exists";

    public static final String VALUE_ALREADY_EXISTS_EXCEPTION = " you want to create already exists";



}
