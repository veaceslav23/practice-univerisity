package com.endava.crudapp.converter;

import com.endava.crudapp.dto.FacultyDto;
import com.endava.crudapp.entity.FacultyEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyDtoToFacultyConverter implements Converter<FacultyDto, FacultyEntity> {
    @Override
    public FacultyEntity convert(FacultyDto facultyDto) {
        return FacultyEntity.builder()
            .facultyName(facultyDto.getFacultyName())
            .build();
    }
}
