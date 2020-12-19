package com.endava.crudapp.service;

import com.endava.crudapp.converter.StudentToStudentDtoConverter;
import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.entity.SearchEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private SearchEntity searchEntity;
    @Autowired
    private StudentService studentService;
    private StudentToStudentDtoConverter converter;

    public List<StudentDto> search(String pattern){

        String firstName = pattern;
        String lastName = pattern;

        return studentService.findByFirstNameOrLastName(firstName,lastName);
    }
}
