package com.sh.JavaTask;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sh.JavaTask.model.JavaTask;
import com.sh.JavaTask.repository.JavaTaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes=JavaTaskApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-integrationtest.properties")
public class JavaTaskControllerTest {

	private JavaTask taskOne;
	
	@Autowired
	private JavaTaskRepository repo;
	
	@Autowired
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		taskOne = new JavaTask("What is polymorphism?", "Sergio", "Medium");
		repo.save(taskOne);
	}
	
	@After
	public void tearDown() {
		repo.deleteAll();
	}
	
	@Test
	public void getIndex_returnHtmlAnd200Status() throws Exception {
		mvc.perform(get("/")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
	}
	
	@Test
	public void givenJavaTasks_getAllJavaTasks_returnsJsonAllJavaTasks() throws Exception {
		mvc.perform(get("/api/tasks")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].difficulty", is("Medium")));
	}

}
