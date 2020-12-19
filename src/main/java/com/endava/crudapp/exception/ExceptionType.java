package com.endava.crudapp.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionType {

    STUDENT_SHOULD_NOT_BE_NULL("Student should not be null"),

    FIRST_NAME_IS_MANDATORY("First Name is mandatory!"),
    FIRST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER("First name should contain 3 to 30 "
                                                                             + "chars!"),
    LAST_NAME_IS_MANDATORY("Last Name is mandatory!"),
    LAST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER("Last name should contain 3 to 30 "
                                                                              + "chars and should start with a "
                                                                              + "capital letter"),
    PHONE_NUMBER_SHOULD_CONSIST_OF_9_DIGITS("Phone number should consist of 9 digits"),
    STUDENT_WITH_THIS_ID_ALREADY_EXISTS("Student exists with id: ");

    private String message;
}
