package com.endava.crudapp.converter;

import com.endava.crudapp.dto.FacultyDto;
import com.endava.crudapp.entity.FacultyEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyToFacultyDtoConverter implements Converter<FacultyEntity, FacultyDto> {
    @Override
    public FacultyDto convert(FacultyEntity facultyEntity) {
        return FacultyDto.builder()
            .id(facultyEntity.getId())
            .facultyName(facultyEntity.getFacultyName())
            .build();
    }
}
