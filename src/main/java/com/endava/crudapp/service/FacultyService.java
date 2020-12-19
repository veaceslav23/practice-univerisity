package com.endava.crudapp.service;

import com.endava.crudapp.entity.FacultyEntity;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    List<FacultyEntity> findAll();

    Optional<FacultyEntity> findById(Long id);

    FacultyEntity save(FacultyEntity facultyEntity);

    void delete(FacultyEntity facultyEntity);
}
