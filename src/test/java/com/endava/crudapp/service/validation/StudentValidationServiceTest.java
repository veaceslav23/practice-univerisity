package com.endava.crudapp.service.validation;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.entity.StudentEntity;
import com.endava.crudapp.exception.StudentServiceException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentValidationServiceTest {

    FacultyEntity facultyEntity = new FacultyEntity(1L,"Law");

    StudentEntity student = new StudentEntity(1L,"veaceslav","cebotari","slava@email.com","078290302",
        facultyEntity);

    StudentValidationService service = new StudentValidationService();

    @Test
    public void whenValidateFirstNameThatBeginsWithCapitalLetterAndConsistOfThreeToThirtyChars_thenShouldNotThrowStudentException(){
        String firstName = "Veaceslav";

        assertDoesNotThrow(() -> service.validateStudentFirstName(firstName));
    }

    @Test
    public void whenValidateLastNameBeginsWithCapitalLetterAndConsistOfThreeToThirtyChars_thenShouldNotThrowStudentException(){
        String lastName = "Cebotari";

        assertDoesNotThrow(() -> service.validateStudentFirstName(lastName));
    }

    @Test
    public void whenValidateNullFirstName_thenShouldThrowStudentException(){
        assertThrows(StudentServiceException.class,() -> service.validateStudentFirstName(null));
    }

    @Test
    public void whenValidateFirstNameThatBeginsWithLowerCase_thenShouldThrowStudentException(){
        assertThrows(StudentServiceException.class,() -> service.validateStudentFirstName(student.getFirstName()));
    }

    @Test
    public void whenValidateFirstNameThatContainsLessThanThreeLetters_thenShouldThrowStudentException(){
        String firstName = "La";

        assertThrows(StudentServiceException.class,() -> service.validateStudentFirstName(firstName));
    }

    @Test
    public void whenValidateFirstNameThatContainsMoreThanThirtyLetters_thenShouldThrowStudentException(){
        String firstName = "StringWithALotOfCharactersIsInvalid";

        assertThrows(StudentServiceException.class,() -> service.validateStudentFirstName(firstName));
    }

    @Test
    public void whenValidateFirstNameThatContainsSpaces_thenShouldThrowStudentException(){
        String firstName = "String with space";

        assertThrows(StudentServiceException.class,() -> service.validateStudentFirstName(firstName));
    }
    //TODO lastName
    @Test
    public void whenValidateNullLastName_thenShouldThrowStudentException(){
        assertThrows(StudentServiceException.class,() -> service.validateStudentLastName(null));
    }

    @Test
    public void whenValidateLastNameThatBeginsWithLowerCase_thenShouldThrowStudentException(){
        assertThrows(StudentServiceException.class,() -> service.validateStudentLastName(student.getLastName()));
    }

    @Test
    public void whenValidateLastNameThatContainsLessThanThreeLetters_thenShouldThrowStudentException(){
        String lastName = "La";

        assertThrows(StudentServiceException.class,() -> service.validateStudentLastName(lastName));
    }

    @Test
    public void whenValidateLastNameThatContainsMoreThanThirtyLetters_thenShouldThrowStudentException(){
        String lastName = "StringWithALotOfCharactersIsInvalid";

        assertThrows(StudentServiceException.class,() -> service.validateStudentLastName(lastName));
    }

    @Test
    public void whenValidateLastNameThatContainsSpaces_thenShouldThrowStudentException(){
        String lastName = "String with space";

        assertThrows(StudentServiceException.class,() -> service.validateStudentFirstName(lastName));
    }

    @Test
    public void whenValidatePhoneNumberThatDoesNotContainOnlyDigits_thenShouldThrowStudentException(){
        String phoneNumber = "078abcsde";

        assertThrows(StudentServiceException.class,() -> service.validateStudentPhoneNumber(phoneNumber));
    }

    @Test
    public void whenValidatePhoneNumberThatDoesNotConsistOnlyOfNineDigits_thenShouldThrowStudentException(){
        String phoneNumber = "0782903022";

        assertThrows(StudentServiceException.class,() -> service.validateStudentPhoneNumber(phoneNumber));
    }

    @Test
    public void whenValidatePhoneNumberThatConsistsOnlyOfNineDigits_thenShouldNotThrowStudentException(){
        String phoneNumber = "078290302";

        assertDoesNotThrow(() -> service.validateStudentPhoneNumber(phoneNumber));
    }

    @Test
    public void whenValidateNullStudent_thenShouldThrowStudentException(){
        assertThrows(StudentServiceException.class, () -> service.validateStudent(null));
    }
}
