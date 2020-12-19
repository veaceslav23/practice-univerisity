package com.endava.crudapp.exception;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class StudentServiceException extends RuntimeException{

    @Getter
    private List<ExceptionType> messages;
    @Getter
    private ExceptionType type;

    public StudentServiceException(String message) {
        super(message);
    }

    public StudentServiceException(List<ExceptionType> errorMessages) {
        super(errorMessages.stream()
                            .map(exception -> exception.getMessage())
                            .collect(Collectors.joining("\n")));
        this.messages = errorMessages;
    }

    public StudentServiceException(ExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public static StudentServiceException of(List<ExceptionType> type){
        return new StudentServiceException(type);
    }

    public static StudentServiceException of(ExceptionType type){
        return new StudentServiceException(type);
    }
}
