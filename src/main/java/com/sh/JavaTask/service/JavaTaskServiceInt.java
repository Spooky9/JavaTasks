package com.sh.JavaTask.service;

import java.util.List;

import com.sh.JavaTask.model.JavaTask;

public interface JavaTaskServiceInt {

	public JavaTask getJavaTaskById(Integer id);
	public List<JavaTask> getAllJavaTasksByDifficulty(String difficulty);
	public List<JavaTask> getAllJavaTasksByAuthor(String author);
	public List<JavaTask> getAllJavaTasks();
	public void createTask(JavaTask javaTask);
	public void updateJavaTaskById(Integer id, JavaTask javaTask);
	public void deleteTaskById(Integer id);
	public void deleteAllTasks();
	public JavaTask randomTask();
}
