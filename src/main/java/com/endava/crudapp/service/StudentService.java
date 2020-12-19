package com.endava.crudapp.service;


import com.endava.crudapp.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> findAll();

    Optional<StudentDto> findById(Long id);

    StudentDto findByEmail(String email);

    StudentDto save(StudentDto StudentDto);

    void deleteById(Long id);

    StudentDto update(Long id, StudentDto newStudent);

    List<StudentDto> findByFirstName(String firstName);

    List<StudentDto> findByFacultyId(Long id);

    List<StudentDto> findByFirstNameOrLastName(String firstName, String lastName);
}
