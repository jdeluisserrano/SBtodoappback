package com.jdeluisserrano.todoapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdeluisserrano.todoapp.entities.TodoEntity;
import com.jdeluisserrano.todoapp.service.TodoService;;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	TodoService todoService;

	@GetMapping("/todos")
	public List<TodoEntity> getAllTodos() {
		return todoService.findAllByCreatedAtDesc();
	}

	@PostMapping("/todos")
	public TodoEntity createTodo(@Valid @RequestBody TodoEntity todo) {
		return todoService.save(todo);
	}

	@GetMapping(value = "/todos/{id}")
	public ResponseEntity<TodoEntity> getTodoById(@PathVariable("id") String id) {
		return todoService.findOne(id);
	}

	@PutMapping(value = "/todos/{id}")
	public ResponseEntity<TodoEntity> updateTodo(@PathVariable("id") String id, @Valid @RequestBody TodoEntity todo) {
		return todoService.updateTodo(id, todo);
	}

	@DeleteMapping(value = "/todos/{id}")
	public ResponseEntity<TodoEntity> deleteTodo(@PathVariable("id") String id) {
		return todoService.delete(id);
	}

}
