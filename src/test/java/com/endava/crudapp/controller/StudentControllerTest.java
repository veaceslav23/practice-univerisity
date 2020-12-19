package com.endava.crudapp.controller;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.exception.StudentServiceException;
import com.endava.crudapp.service.FacultyService;
import com.endava.crudapp.service.SearchService;
import com.endava.crudapp.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private StudentService service;
    @MockBean
    private FacultyService facultyService;
    @MockBean
    private SearchService searchService;

    private final FacultyEntity facultyEntity = new FacultyEntity(101L,"Law");

    @Test
    public void givenStudents_whenGetStudents_thenReturnJsonAndStatusOk()
        throws Exception {
        StudentDto firstStudent = new StudentDto(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);
        StudentDto secondStudent = new StudentDto(1L,"Second","Student","second@email.com","07829030",
            facultyEntity);
        StudentDto thirdStudent = new StudentDto(1L,"Third","Student","third@email.com","078290301",
            facultyEntity);

        when(service.findAll()).thenReturn(Arrays.asList(firstStudent,secondStudent,thirdStudent));

        mockMvc.perform(get("/students/all")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(firstStudent,secondStudent,thirdStudent))));
    }

    @Test
    public void notGivenStudents_whenGetStudents_thenReturnEmptyJson()
        throws Exception {
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/students/all")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", empty()));
    }

    @Test
    public void validStudent_whenSaveStudent_thenShouldReturnStatusAccepted()
        throws Exception {
        StudentDto firstStudent = new StudentDto(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(service.save(firstStudent)).thenReturn(firstStudent);

        mockMvc.perform(post("/student/new")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsBytes(firstStudent)))
            .andExpect(status().isAccepted());
    }

    @Test
    public void givenInvalidStudent_whenSaveStudent_thenShouldReturnStatusBadRequest()
        throws Exception {
        StudentDto firstStudent = new StudentDto(1L,"veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(service.save(firstStudent)).thenThrow(StudentServiceException.class);

        mockMvc.perform(post("/student/new")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsBytes(firstStudent)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void givenStudent_whenFindById_thenShouldReturnStatusOk()
        throws Exception {
        StudentDto firstStudent = new StudentDto(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(service.findById(firstStudent.getId())).thenReturn(Optional.of(firstStudent));

        mockMvc.perform(get("/student/id/{id}", 1L)
            .content(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(firstStudent)));
    }

    @Test
    public void givenStudents_whenFindByFirstName_thenShouldReturnStatusOk()
        throws Exception {
        StudentDto firstStudent = new StudentDto(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(service.findByFirstName(firstStudent.getFirstName())).thenReturn(Collections.singletonList(firstStudent));

        mockMvc.perform(get("/student/firstName/{firstName}", "Veaceslav")
            .content(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }

    @Test
    public void NotGivenStudents_whenFindByFirstName_thenShouldReturnEmptyJson()
        throws Exception {
        when(service.findByFirstName("Veaceslav")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/student/firstName/{firstName}", "Veaceslav")
            .content(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$", empty()));
    }

    @Test
    public void givenStudent_whenDeleteById_thenShouldReturnStatusOk()
        throws Exception {
        StudentDto firstStudent = new StudentDto(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(service.findById(firstStudent.getId())).thenReturn(Optional.of(firstStudent));

        mockMvc.perform(delete("/deleted-student/{id}",firstStudent.getId()))
            .andExpect(status().isOk());
    }
}
