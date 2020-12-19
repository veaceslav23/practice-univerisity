package com.endava.crudapp;

import com.endava.crudapp.dto.StudentDto;
import com.endava.crudapp.entity.FacultyEntity;
import com.endava.crudapp.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CrudAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StudentService studentService;

	private FacultyEntity facultyEntity = new FacultyEntity(101L,"Law");

	@Test
	void contextLoads() throws Exception{
		StudentDto firstStudent = new StudentDto(1L,"Veaceslav","Cebotari","slava@email.com","078290302",
			facultyEntity);

		mockMvc.perform(post("/student/new")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(objectMapper.writeValueAsString(firstStudent)))
			.andExpect(status().isAccepted());

		StudentDto studentDto = studentService.findByEmail(firstStudent.getEmail());

		assertThat(studentDto.getEmail()).isEqualTo("slava@email.com");
	}

}
