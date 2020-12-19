package com.endava.crudapp.service;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.repository.FacultyRepository;
import com.endava.crudapp.service.impl.FacultyServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyServiceImpl facultyService;

    @Test
    public void whenFindAll_thenShouldReturnAllFaculties(){

        FacultyEntity firstFaculty = new FacultyEntity(101L,"Law");
        FacultyEntity secondFaculty = new FacultyEntity(102L,"Economics");
        FacultyEntity thirdFaculty = new FacultyEntity(103L,"Maths");

        when(facultyRepository.findAll()).thenReturn(Arrays.asList(firstFaculty,secondFaculty,thirdFaculty));

        List<FacultyEntity> actualResult = facultyService.findAll();

        assertThat(actualResult).containsExactly(firstFaculty,secondFaculty,thirdFaculty);
    }

    @Test
    public void whenFacultiesDoNotExist_thenShouldReturnEmptyOptional(){

        when(facultyRepository.findAll()).thenReturn(Collections.emptyList());

        List<FacultyEntity> actualResult = facultyService.findAll();

        assertThat(actualResult).isEmpty();
    }

    @Test
    public void whenFindByExistentId_thenShouldReturnFaculty(){

        FacultyEntity firstFaculty = new FacultyEntity(101L,"Law");

        Long id = 101L;

        when(facultyRepository.findById(id)).thenReturn(Optional.of(firstFaculty));

        Optional<FacultyEntity> actualResult = facultyService.findById(id);

        assertThat(actualResult).isEqualTo(Optional.of(firstFaculty));
    }
}
