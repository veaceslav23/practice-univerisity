package com.endava.crudapp.dto;

import com.endava.crudapp.entity.FacultyEntity;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StudentDto {
    @Nullable
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private FacultyEntity facultyEntity;
}
