package com.endava.crudapp.repository;

import com.endava.crudapp.entity.FacultyEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindById_thenReturnFaculty() {
        // given
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setFacultyName("Law");

        entityManager.persist(facultyEntity);
        entityManager.flush();

        // when
        FacultyEntity found = facultyRepository.getOne(1L);

        // then
        assertThat(found)
            .isEqualTo(facultyEntity);
    }
}
