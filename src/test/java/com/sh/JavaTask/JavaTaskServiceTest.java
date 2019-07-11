package com.sh.JavaTask;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sh.JavaTask.model.JavaTask;
import com.sh.JavaTask.repository.JavaTaskRepository;
import com.sh.JavaTask.service.JavaTaskServiceImpl;
import com.sh.JavaTask.service.JavaTaskServiceInt;

@RunWith(SpringRunner.class)
public class JavaTaskServiceTest {

	private JavaTask taskOne;
	
	@Configuration
	static class ServiceConfiguration{
		
		@Bean
		public JavaTaskServiceInt config() {
			return new JavaTaskServiceImpl();
		}
	}
	
	@Autowired
	private JavaTaskServiceImpl service;
	
	@MockBean
	private JavaTaskRepository repo;
	
	@Before
	public void setUp() {
		taskOne = new JavaTask("List all core java annotations", "Sergio","Medium");
	}
	
	@Test
	public void givenId_returnCorrectJavaTask() {
		Mockito.when(repo.findJavaTaskById(taskOne.getId()))
		.thenReturn(taskOne);
		
		JavaTask found = service.getJavaTaskById(taskOne.getId());
		
		assertEquals(taskOne, found);
	}
}
