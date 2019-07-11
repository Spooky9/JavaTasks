package com.sh.JavaTask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sh.JavaTask.model.JavaTask;
import com.sh.JavaTask.service.JavaTaskServiceImpl;

@Controller
public class JavaTaskController {

	@Autowired
	private JavaTaskServiceImpl service;
	
	@GetMapping("/")
	public String index() {
		return "home";
	}
	
	@GetMapping("/tasks")
	public String allJavaTasks(Model model, JavaTask javaTask) {
		List<JavaTask> tasks = service.getAllJavaTasks();
		model.addAttribute("allTasks", tasks);
		return "tasks";
	}
	
	@GetMapping("/task/{id}")
	public String findById(@PathVariable("id") Integer id, Model model) {
		JavaTask taskObj = service.getJavaTaskById(id);
		model.addAttribute("taskItem", taskObj);
		return "task";
	}
	 
	@GetMapping("/task/new")
	public String getJavaTaskForm(JavaTask javaTask) {
		return "newTask";
	}
	
	@PostMapping("/task/new")
	public String createJoke(JavaTask javaTask, Model model){
		service.createTask(javaTask);
		model.addAttribute("message", "Task created! Click to go to task");
		return "newTask";
	}
	
	@GetMapping("/task/{id}/update")
	public String updateForm(@PathVariable("id") Integer id, JavaTask javaTask, Model model) {
		JavaTask currentTask = service.getJavaTaskById(id);
		model.addAttribute("taskItem", currentTask);
		return "updateTask";
	}
	
	@PostMapping("/task/{id}/update")
	public String updateTaskById(@PathVariable("id") Integer id, JavaTask javaTask, Model model) {
		service.updateJavaTaskById(id, javaTask);
		model.addAttribute("message", "Task updated Successfully");
		return "result";
	}
	
	@DeleteMapping("/task/{id}")
	public String deleteTaskById(@PathVariable("id") Integer id, Model model) {
		service.deleteTaskById(id);
		model.addAttribute("message", "Task deleted Successfully");
		return "result";
	}
	
	@DeleteMapping("/tasks")
	public String deleteAllTasks(Model model) {
		service.deleteAllTasks();
		model.addAttribute("message", "All tasks deleted Successfully");
		return "result";
	}
}