package com.jdeluisserrano.todoapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jdeluisserrano.todoapp.entities.TodoEntity;
import com.jdeluisserrano.todoapp.repositories.TodoRepository;
import com.jdeluisserrano.todoapp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<TodoEntity> findAllByCreatedAtDesc() {
		logger.info("Getting all To-do's");
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return todoRepository.findAll(sortByCreatedAtDesc);
	}

	@Override
	public TodoEntity save(TodoEntity todoEntity) {
		logger.info("Saving a new To-do: " + todoEntity.toString());
		todoEntity.setName(null);
		todoEntity.setTags(null);
		todoEntity.setCheckboxesEnabled(true);
		todoEntity.setViewed(false);
		return todoRepository.save(todoEntity);
	}

	@Override
	public ResponseEntity<TodoEntity> findOne(String id) {
		logger.info("Getting To-do " + id);
		TodoEntity todo = todoRepository.findOne(id);
		ResponseEntity<TodoEntity> response;
		if (todo == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(todo, HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ResponseEntity<TodoEntity> updateTodo(String id, TodoEntity todo) {
		logger.info("Updating To-do " + id + " TodoEntity = " + todo.toString());
		
		TodoEntity todoData = todoRepository.findOne(id);
		ResponseEntity<TodoEntity> response;
		if (todoData == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			todoData.setTitle(todo.getTitle());
			todoData.setViewed(todo.getViewed());
			todoData.setCheckboxesEnabled(todo.getCheckboxesEnabled());
			todoData.setName(todo.getName());
			todoData.setTags(todo.getTags());
			TodoEntity updatedTodo = todoRepository.save(todoData);
			response = new ResponseEntity<>(updatedTodo, HttpStatus.OK);
		}
		return response;
	}

	@Override
	public ResponseEntity<TodoEntity> delete(String id) {
		logger.info("Deleting To-do " + id);
		ResponseEntity<TodoEntity> response;
		if(todoRepository.exists(id)) {
			todoRepository.delete(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		}else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

}
