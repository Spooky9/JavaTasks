package com.sh.JavaTask;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sh.JavaTask.model.JavaTask;
import com.sh.JavaTask.repository.JavaTaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JavaTaskRepositoryTest {

	private JavaTask taskOne;
	private JavaTask taskTwo;
	private JavaTask taskThree;
	
	@Autowired
	private JavaTaskRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Before
	public void setUp() {
		taskOne = new JavaTask("Create a LinkedList", "Sergio", "Easy");
		taskTwo = new JavaTask("List all data structures", "Sergio", "Easy");
		taskThree = new JavaTask("Reverse a string", "Sergio", "Medium");
		entityManager.persistAndFlush(taskOne);
		entityManager.persistAndFlush(taskTwo);
		entityManager.persistAndFlush(taskThree);
	}
	
	@Test
	public void findById_shouldReturnCorrectJavaTask() {
		JavaTask foundTask = repo.findJavaTaskById(taskOne.getId());
		assertEquals(taskOne, foundTask);
	}
	
	@Test
	public void findAllByDifficulty_ShouldReturnAllJavaExercise() {
		List<JavaTask> tasks = Arrays.asList(taskOne, taskTwo);
		List<JavaTask> foundTask = repo.findAllByDifficulty(taskOne.getDifficulty());
		
		assertEquals(tasks, foundTask);
	}
	
	@Test
	public void findAllShouldReturnListOfAllExercises() {
		List<JavaTask> exercises = Arrays.asList(taskOne, taskTwo, taskThree);
		List<JavaTask> foundExercises = repo.findAll();
		
		assertEquals(exercises, foundExercises);
	}
}