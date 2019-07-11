package com.sh.JavaTask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sh.JavaTask.model.JavaTask;

@Repository
public interface JavaTaskRepository extends JpaRepository<JavaTask, Integer>{

	public List<JavaTask> findAll();
	public JavaTask findJavaTaskById(Integer id);
	public List<JavaTask> findAllByDifficulty(String difficulty);
	public List<JavaTask> findAllByAuthor(String author);
}
