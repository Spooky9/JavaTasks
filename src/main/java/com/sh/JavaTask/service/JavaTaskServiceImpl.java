package com.sh.JavaTask.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.JavaTask.model.JavaTask;
import com.sh.JavaTask.repository.JavaTaskRepository;

@Service
public class JavaTaskServiceImpl implements JavaTaskServiceInt{

	@Autowired
	private JavaTaskRepository repo;

	@Override
	public JavaTask getJavaTaskById(Integer id) {
		return repo.findJavaTaskById(id);
	}

	@Override
	public List<JavaTask> getAllJavaTasksByDifficulty(String difficulty) {
		return repo.findAllByDifficulty(difficulty);
	}

	@Override
	public List<JavaTask> getAllJavaTasks() {
		return repo.findAll();
	}

	@Override
	public void createTask(JavaTask javaTask) {
		repo.save(javaTask);
	}

	@Override
	public void updateJavaTaskById(Integer id, JavaTask javaTask) {
		JavaTask taskToReplace = repo.findJavaTaskById(id);
		String task = javaTask.getTask();
		String author = javaTask.getAuthor();
		String difficulty = javaTask.getDifficulty();
		if(task != null) taskToReplace.setTask(task);
		if(author != null) taskToReplace.setAuthor(author);
		if(difficulty != null) taskToReplace.setDifficulty(difficulty);
		repo.save(taskToReplace);
	}

	@Override
	public void deleteTaskById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public void deleteAllTasks() {
		repo.deleteAll();
	}

	@Override
	public List<JavaTask> getAllJavaTasksByAuthor(String author) {
		return repo.findAllByAuthor(author);
	}

	@Override
	public JavaTask randomTask() {
		List<JavaTask> tasks = repo.findAll();
		tasks.size();
		Random randomTask = new Random();
		return tasks.get(randomTask.nextInt(tasks.size()));
	}
}
