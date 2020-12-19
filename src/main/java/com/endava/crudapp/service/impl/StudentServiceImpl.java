package com.endava.crudapp.service.impl;

import com.endava.crudapp.converter.StudentDtoToStudentConverter;
import com.endava.crudapp.converter.StudentToStudentDtoConverter;
import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.entity.StudentEntity;
import com.endava.crudapp.exception.StudentServiceException;
import com.endava.crudapp.repository.FacultyRepository;
import com.endava.crudapp.repository.StudentRepository;
import com.endava.crudapp.service.StudentService;
import com.endava.crudapp.service.validation.StudentValidationService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentValidationService studentValidationService;
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;
    private final StudentDtoToStudentConverter studentDtoToStudentConverter;

    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentToStudentDtoConverter::convert).collect(toList());
    }

    public Optional<StudentDto> findById(Long id) {
        return studentRepository.findById(id).map(studentToStudentDtoConverter::convert);
    }

    public StudentDto findByEmail(String email) {
        return studentToStudentDtoConverter.convert(studentRepository.findByEmail(email));
    }

    public StudentDto save(StudentDto studentDto) {

        StudentEntity studentEntity = studentDtoToStudentConverter.convert(studentDto);

        studentValidationService.validateStudent(studentEntity);

        if(studentRepository.findByEmail(studentDto.getEmail()) != null) {
            throw new StudentServiceException("Student with email " + studentDto.getEmail() + " exists");
        }

        return studentToStudentDtoConverter.convert(studentRepository.save(studentEntity));
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentDto update(Long id, StudentDto newStudent) {
        StudentEntity studentEntity = studentDtoToStudentConverter.convert(newStudent);

        return studentToStudentDtoConverter.convert(studentRepository.findById(id)
            .map(student -> {
                student.setFirstName(newStudent.getFirstName());
                student.setLastName(newStudent.getLastName());
                student.setEmail(newStudent.getEmail());
                student.setPhoneNumber(newStudent.getPhoneNumber());
                student.setFacultyEntity(newStudent.getFacultyEntity());
                return studentRepository.save(studentEntity);
            })
            .orElseGet(() -> {
                newStudent.setId(id);
                return studentRepository.save(studentEntity);
            }));
    }
    public List<StudentDto> findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName).stream().map(studentToStudentDtoConverter::convert).collect(toList());
    }
    public List<StudentDto> findByFacultyId(Long id) {
        FacultyEntity facultyEntity = facultyRepository.getOne(id);

        return studentRepository.findByFacultyEntity(facultyEntity).stream().map(studentToStudentDtoConverter::convert).collect(toList());
    }

    public List<StudentDto> findByFirstNameOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(firstName,lastName).stream().map(studentToStudentDtoConverter::convert).collect(toList());
    }
}
