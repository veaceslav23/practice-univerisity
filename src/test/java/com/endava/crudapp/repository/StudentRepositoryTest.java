package com.endava.crudapp.repository;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.entity.StudentEntity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public static Stream<StudentEntity> parameters(){
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setFacultyName("Law");

        StudentEntity student = new StudentEntity();
        student.setFirstName("Veaceslav");
        student.setLastName("Cebotari");
        student.setEmail("slava@email.com");
        student.setPhoneNumber("078290302");
        student.setFacultyEntity(facultyEntity);

        return Stream.of(student);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void whenFindById_thenReturnStudent(StudentEntity studentEntity){

        entityManager.persist(studentEntity);
        entityManager.flush();

        StudentEntity foundStudent = studentRepository.getOne(1L);

        assertThat(foundStudent).isEqualTo(studentEntity);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void whenFindByFirstName_thenReturnListOfStudent(StudentEntity studentEntity){

        entityManager.persist(studentEntity);
        entityManager.flush();

        List<StudentEntity> foundStudents = studentRepository.findByFirstName(studentEntity.getFirstName());

        assertThat(foundStudents).containsExactly(studentEntity);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void whenFindByEmail_thenReturnOneStudent(StudentEntity studentEntity){

        entityManager.persist(studentEntity);
        entityManager.flush();

        StudentEntity foundStudent = studentRepository.findByEmail(studentEntity.getEmail());

        assertThat(foundStudent).isEqualTo(studentEntity);
    }
}
