package com.jdeluisserrano.todoapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jdeluisserrano.todoapp.entities.TodoEntity;

public interface TodoService {
	
	public List<TodoEntity> findAllByCreatedAtDesc();
	
	public TodoEntity save(TodoEntity todoEntity);
	
	public ResponseEntity<TodoEntity> findOne(String id);
	
	public ResponseEntity<TodoEntity> updateTodo(String id, TodoEntity todo);
	
	public ResponseEntity<TodoEntity> delete(String id);
	
}
