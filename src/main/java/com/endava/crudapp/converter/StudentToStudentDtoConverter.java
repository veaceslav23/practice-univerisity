package com.endava.crudapp.converter;

import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.entity.StudentEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentDtoConverter implements Converter<StudentEntity, StudentDto> {
    @Override
    public StudentDto convert(StudentEntity studentEntity) {
        return StudentDto.builder()
            .id(studentEntity.getId())
            .firstName(studentEntity.getFirstName())
            .lastName(studentEntity.getLastName())
            .email(studentEntity.getEmail())
            .phoneNumber(studentEntity.getPhoneNumber())
            .facultyEntity(studentEntity.getFacultyEntity())
            .build();
    }
}
