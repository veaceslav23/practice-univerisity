package com.endava.crudapp.service.impl;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.repository.FacultyRepository;
import com.endava.crudapp.service.FacultyService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public List<FacultyEntity> findAll() {
        return facultyRepository.findAll();
    }

    public Optional<FacultyEntity> findById(Long id) {
        return facultyRepository.findById(id);
    }

    public FacultyEntity save(FacultyEntity facultyEntity) {
        return facultyRepository.save(facultyEntity);
    }

    public void delete(FacultyEntity facultyEntity) {
        facultyRepository.delete(facultyEntity);
    }
}
