package com.endava.crudapp.service.validation;

import com.endava.crudapp.entity.StudentEntity;
import com.endava.crudapp.exception.StudentServiceException;

import org.springframework.stereotype.Service;

import static com.endava.crudapp.exception.ExceptionType.FIRST_NAME_IS_MANDATORY;
import static com.endava.crudapp.exception.ExceptionType.FIRST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER;
import static com.endava.crudapp.exception.ExceptionType.LAST_NAME_IS_MANDATORY;
import static com.endava.crudapp.exception.ExceptionType.LAST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER;
import static com.endava.crudapp.exception.ExceptionType.PHONE_NUMBER_SHOULD_CONSIST_OF_9_DIGITS;
import static com.endava.crudapp.exception.ExceptionType.STUDENT_SHOULD_NOT_BE_NULL;

@Service
public class StudentValidationService {

//    @Getter
//    private List<ExceptionType> errorMessages;

    public void validateStudent(StudentEntity studentEntity){
        if(studentEntity == null){
            throw StudentServiceException.of(STUDENT_SHOULD_NOT_BE_NULL);
        }
        validateStudentFirstName(studentEntity.getFirstName());
        validateStudentLastName(studentEntity.getLastName());
        validateStudentPhoneNumber(studentEntity.getPhoneNumber());
    }

    public void validateStudentFirstName(String firstName){
        if(firstName == null){
            throw StudentServiceException.of(FIRST_NAME_IS_MANDATORY);
            //errorMessages.add(FIRST_NAME_IS_MANDATORY);
        }

        if(!firstName.matches("^[A-Z][a-zA-Z]{2,30}")){
            throw StudentServiceException.of(FIRST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER);
            //errorMessages.add(FIRST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER);
        }
    }
    public void validateStudentLastName(String lastName){
        if(lastName == null){
            throw StudentServiceException.of(LAST_NAME_IS_MANDATORY);
            //errorMessages.add(LAST_NAME_IS_MANDATORY);
        }

        if(!lastName.matches("^[A-Z][a-zA-Z]{2,30}")){
            throw StudentServiceException.of(LAST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER);
            //errorMessages.add(LAST_NAME_SHOULD_BE_OF_3_TO_30_CHARS_AND_SHOULD_START_WITH_CAPITAL_LETTER);
        }
    }
    public void validateStudentPhoneNumber(String phoneNumber){
        if(!phoneNumber.matches("^\\d{9}$")){
            throw StudentServiceException.of(PHONE_NUMBER_SHOULD_CONSIST_OF_9_DIGITS);
            //errorMessages.add(PHONE_NUMBER_SHOULD_CONSIST_OF_9_DIGITS);
        }
    }
}
