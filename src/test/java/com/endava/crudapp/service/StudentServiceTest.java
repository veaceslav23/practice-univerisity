package com.endava.crudapp.service;

import com.endava.crudapp.converter.StudentToStudentDtoConverter;
import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.entity.StudentEntity;
import com.endava.crudapp.exception.StudentServiceException;
import com.endava.crudapp.repository.FacultyRepository;
import com.endava.crudapp.repository.StudentRepository;
import com.endava.crudapp.service.impl.StudentServiceImpl;
import com.endava.crudapp.service.validation.StudentValidationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private StudentValidationService studentValidationService;
    @InjectMocks
    private StudentServiceImpl studentService;
    private StudentToStudentDtoConverter converter;
    @Captor
    private ArgumentCaptor<StudentEntity> StudentEntityArgumentCaptor;

    FacultyEntity facultyEntity = new FacultyEntity(101L,"Law");

    @Test
    public void shouldSaveAStudent(){
        StudentEntity student = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
                                facultyEntity);

        when(studentRepository.save(student)).thenReturn(student);


        StudentDto newStudent = studentService.save(converter.convert(student));

        verify(studentValidationService).validateStudent(StudentEntityArgumentCaptor.capture());

        assertEquals(converter.convert(student),newStudent);
    }

    @Test
    public void whenSavingStudentWithSameEmail_thenShouldThrowStudentException(){
        FacultyEntity facultyEntity = new FacultyEntity(101L,"Law");

        StudentEntity firstStudent = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);
        StudentEntity copy = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(studentRepository.save(firstStudent)).thenReturn(firstStudent);

        studentService.save(converter.convert(firstStudent));

        when(studentRepository.findByEmail(copy.getEmail())).thenReturn(firstStudent);

        verify(studentValidationService).validateStudent(StudentEntityArgumentCaptor.capture());

        assertThrows(StudentServiceException.class,() -> studentService.save(converter.convert(copy)));
    }

    @Test
    public void findAllStudents(){
        StudentEntity firstStudent = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);
        StudentEntity secondStudent = new StudentEntity(1L,"Second","Student","second@email.com","07829030",
            facultyEntity);
        StudentEntity thirdStudent = new StudentEntity(1L,"Third","Student","third@email.com","078290301",
            facultyEntity);

        when(studentRepository.findAll()).thenReturn(Arrays.asList(firstStudent,secondStudent,thirdStudent));

        List<StudentDto> studentList = studentService.findAll();

        assertThat(studentList).containsExactly(converter.convert(firstStudent),converter.convert(secondStudent),
            converter.convert(thirdStudent));
    }

    @Test
    public void whenNoStudents_thenFindShouldReturnEmptyList(){
        List<StudentEntity> emptyList = Collections.emptyList();

        when(studentRepository.findAll()).thenReturn(emptyList);

        assertTrue(studentService.findAll().isEmpty());
    }

    @Test
    public void shouldUpdateStudentWithGivenId(){
        StudentEntity student = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        lenient().when(studentRepository.getOne(1L)).thenReturn(student);

        StudentEntity expectedResult = new StudentEntity(1L,"Student","update","slava@email.com","078290302",
            facultyEntity);

        when(studentRepository.save(expectedResult)).thenReturn(expectedResult);

        StudentDto actualResult = studentService.update(1L, converter.convert(expectedResult));

        assertEquals(converter.convert(expectedResult), actualResult);
    }

    @Test
    public void shouldFindByEmail(){
        StudentEntity expected = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        when(studentRepository.findByEmail(expected.getEmail())).thenReturn(expected);

        assertEquals(converter.convert(expected), studentService.findByEmail(expected.getEmail()));
    }

    @Test
    public void shouldFindByFacultyId(){
        StudentEntity firstStudent = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);
        StudentEntity secondStudent = new StudentEntity(2L,"Second","Student","second@email.com","07829030",
            facultyEntity);

        when(facultyRepository.getOne(facultyEntity.getId())).thenReturn(facultyEntity);
        when(studentRepository.findByFacultyEntity(facultyEntity)).thenReturn(Arrays.asList(firstStudent,secondStudent));

        List<StudentDto> foundByFaculty = studentService.findByFacultyId(facultyEntity.getId());

        assertThat(foundByFaculty).containsExactly(converter.convert(firstStudent),converter.convert(secondStudent));
    }

    @Test
    public void whenNoStudentsWithGivenFacultyId_thenShouldReturnEmptyList(){
        List<StudentEntity> emptyList = Collections.emptyList();

        when(facultyRepository.getOne(facultyEntity.getId())).thenReturn(facultyEntity);
        when(studentRepository.findByFacultyEntity(facultyEntity)).thenReturn(emptyList);

        List<StudentDto> actualResult = studentService.findByFacultyId(facultyEntity.getId());

        verify(facultyRepository).getOne(facultyEntity.getId());

        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void shouldFindByFirstName(){
        StudentEntity firstStudent = new StudentEntity(1L,"Fred","Cebotari","slava@email.com","078290302",
            facultyEntity);
        StudentEntity secondStudent = new StudentEntity(2L,"Fred","Student","second@email.com","07829030",
            facultyEntity);

        String pattern = "Fred";

        when(studentRepository.findByFirstName(pattern)).thenReturn(Arrays.asList(firstStudent,secondStudent));

        List<StudentDto> students = studentService.findByFirstName(pattern);

        assertThat(students).containsExactly(converter.convert(firstStudent),converter.convert(secondStudent));
    }

    @Test
    public void shouldFindByFirstNameOrLastName(){
        StudentEntity firstStudent = new StudentEntity(1L,"Fred","Cebotari","slava@email.com","078290302",
            facultyEntity);
        StudentEntity secondStudent = new StudentEntity(2L,"Student","Fred","second@email.com","07829030",
            facultyEntity);

        String pattern = "Fred";

        when(studentRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(pattern, pattern)).thenReturn(Arrays.asList(firstStudent,secondStudent));

        List<StudentDto> students = studentService.findByFirstNameOrLastName(pattern, pattern);

        assertThat(students).containsExactly(converter.convert(firstStudent),converter.convert(secondStudent));
    }

    @Test
    public void whenNoStudentFoundByFirstNameOrLastName_thenShouldReturnEmptyList(){
        String pattern = "Fred";

        when(studentRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(pattern, pattern)).thenReturn(Collections.emptyList());

        List<StudentDto> actualResult = studentService.findByFirstNameOrLastName(pattern, pattern);

        assertThat(actualResult).isEmpty();
    }

    @Test
    public void shouldFindById(){
        StudentEntity firstStudent = new StudentEntity(1L,"Fred","Cebotari","slava@email.com","078290302",
            facultyEntity);

        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.of(firstStudent));

        Optional<StudentDto> student = studentService.findById(id);

        assertThat(student).containsSame(converter.convert(firstStudent));
    }

    @Test
    public void whenNoStudentWithGivenIdThenShouldReturnEmptyOptional(){
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<StudentDto> actualResult = studentService.findById(id);

        assertThat(actualResult).isEmpty();
    }

    @Test
    public void shouldDeleteStudent(){
        StudentEntity student = new StudentEntity(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
            facultyEntity);

        Long id = 1L;

        studentService.deleteById(id);

        verify(studentRepository).delete(StudentEntityArgumentCaptor.capture());

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<StudentDto> actualResult = studentService.findById(id);

        assertAll(
            () -> assertThat(actualResult).isEmpty()
        );


    }

}
