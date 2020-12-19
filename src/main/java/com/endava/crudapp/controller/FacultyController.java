package com.endava.crudapp.controller;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.service.FacultyService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/faculty/id/{id}")
    @ResponseBody
    public Optional<FacultyEntity> findById(@PathVariable("id") Long id) {
        return facultyService.findById(id);
    }

}
