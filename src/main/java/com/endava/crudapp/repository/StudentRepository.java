package com.endava.crudapp.repository;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.entity.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByFirstName(String firstName);

    List<StudentEntity> findByFacultyEntity(FacultyEntity facultyEntity);

    List<StudentEntity> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);

    StudentEntity findByEmail(String email);
}
