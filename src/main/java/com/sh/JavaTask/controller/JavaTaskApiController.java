package com.sh.JavaTask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.JavaTask.model.JavaTask;
import com.sh.JavaTask.service.JavaTaskServiceImpl;

@RestController
@RequestMapping("/api")
public class JavaTaskApiController {

	@Autowired
	private JavaTaskServiceImpl service;
	
	@GetMapping("/task/{id}")
	public JavaTask getTaskById(@PathVariable Integer id){
		return service.getJavaTaskById(id);
	}
	
	@GetMapping("/tasks/{difficulty}")
	public List<JavaTask> getTasksByDifficulty(@PathVariable String difficulty){
		return service.getAllJavaTasksByDifficulty(difficulty);
	}
	
	@GetMapping("/tasks/{author}")
	public List<JavaTask> getTasksByAuthor(@PathVariable String author){
		return service.getAllJavaTasksByAuthor(author);
	}
	
	@GetMapping("/tasks")
	public List<JavaTask> getAllTasks(){
		return service.getAllJavaTasks();
	}
	
	@PostMapping("/task/new")
	public String createTask(JavaTask javaTask) {
		service.createTask(javaTask);
		return "Task Created!";
	}
	
	@PostMapping("/task/{id}")
	public String updateTask(@PathVariable Integer id, JavaTask javaTask) {
		service.updateJavaTaskById(id, javaTask);
		return "Update Successful!";
	}
	
	@DeleteMapping("/task/{id}")
	public String deleteTaskById(@PathVariable Integer id) {
		service.deleteTaskById(id);
		return "Task Deleted!";
	}
	
}
