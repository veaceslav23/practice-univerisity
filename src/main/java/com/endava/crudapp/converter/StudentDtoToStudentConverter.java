package com.endava.crudapp.converter;

import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.entity.StudentEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoToStudentConverter implements Converter<StudentDto, StudentEntity> {
    @Override
    public StudentEntity convert(StudentDto studentDto) {
        return StudentEntity.builder()
            .firstName(studentDto.getFirstName())
            .lastName(studentDto.getLastName())
            .email(studentDto.getEmail())
            .phoneNumber(studentDto.getPhoneNumber())
            .facultyEntity(studentDto.getFacultyEntity())
            .build();
    }
}
