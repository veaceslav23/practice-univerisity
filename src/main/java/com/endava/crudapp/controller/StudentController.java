package com.endava.crudapp.controller;

import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.entity.SearchEntity;
import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.exception.StudentServiceException;
import com.endava.crudapp.service.FacultyService;
import com.endava.crudapp.service.SearchService;
import com.endava.crudapp.service.StudentService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final FacultyService facultyService;
    private final SearchService searchService;
    private String errorMessage;


    @GetMapping(value = "/students/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findAllStudents() {
        return ResponseEntity.ok().body(studentService.findAll());
    }

    @GetMapping("/student/id/{id}")
    @ResponseBody
    public Optional<StudentDto> findStudentById(@PathVariable("id") Long id) {
        return studentService.findById(id);
    }

    @GetMapping(value = "/student/firstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentDto> findStudentByFirstName(@PathVariable("firstName") String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @GetMapping("/faculty")
    @ResponseBody
    public List<StudentDto> findByFacultyId(@RequestParam("id") Long id) {
        return studentService.findByFacultyId(id);
    }

    @PostMapping(value = "/student/new")
    @ResponseBody
    public ResponseEntity create(@RequestBody StudentDto StudentDto) {

        try {
            return ResponseEntity.accepted().body(studentService.save(StudentDto));
        }
        catch (StudentServiceException exception){
            return ResponseEntity.badRequest()
                .body(exception.getMessage());
        }
    }

    @DeleteMapping("/deleted-student/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        studentService.deleteById(id);

        return ResponseEntity.ok().body("Deleted successfully");
    }

    @GetMapping(value = "/")
    public String prepareSearch(Model model, HttpServletRequest request) {

        model.addAttribute("search", new SearchEntity());

        return "index";
    }

    @GetMapping(value = "/students/result")
    public String findStudentByFirstNameOrLastName(@RequestParam(value = "pattern") String pattern,
                                                   Model model) {
        List<StudentDto> students = searchService.search(pattern);

        model.addAttribute("students", students);
        model.addAttribute("search", new SearchEntity());

        return "search-result";
    }

    @GetMapping("/students")
    public String findAll(Model model) {

        List<StudentDto> students = studentService.findAll();

        model.addAttribute("search", new SearchEntity());
        model.addAttribute("students",students);

        return "show-students";
    }

    @GetMapping("/new")
    public String save(Model model, StudentDto StudentDto) {

        List<FacultyEntity> faculties = facultyService.findAll();

        model.addAttribute("search", new SearchEntity());
        model.addAttribute("faculties",faculties);
        model.addAttribute("errorMsg", errorMessage);

        return "add-student";
    }

    @PostMapping("/new")
    public String saveStudent(StudentDto StudentDto){

        try {
            studentService.save(StudentDto);
        }
        catch (StudentServiceException exception){
            errorMessage = exception.getMessage();

            return "redirect:/new";
        }
        return "redirect:/students";
    }

    @GetMapping("/updated/{id}")
    public String updatePreparing(@PathVariable("id") Long id, Model model) {

        Optional<StudentDto> student = studentService.findById(id);
        List<FacultyEntity> faculties = facultyService.findAll();

        model.addAttribute("search", new SearchEntity());
        model.addAttribute("faculties",faculties);
        model.addAttribute("student",student);

        return "update-student";
    }

    @PostMapping("/updated/{id}")
    public String update(@PathVariable("id") Long id, StudentDto StudentDto) {

        studentService.update(id,StudentDto);

        return "redirect:/students";
    }

    @GetMapping("/deleted/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {

        studentService.deleteById(id);

        return "redirect:/students";
    }
}
