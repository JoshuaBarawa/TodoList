package com.joshua.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshua.app.model.Todo;
import com.joshua.app.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public void addTodo(Todo todo) {
		todoRepository.save(todo);
	}
	
	public List<Todo> getTodos(Long id) {
		return todoRepository.findAllByUserId(id);
	}
	
	public void updateTodo(Long id, Todo todo) {
		Todo todo1 = todoRepository.findById(id).get();
		todo1.setName(todo.getName());
		todo1.setDescription(todo.getDescription());
		todo1.setStatus(todo.getStatus());
		todo1.setUserId(todo1.getUserId());
		
		todoRepository.save(todo1);
	
	}
		
     public String deleteTodo(Long id) {
    	todoRepository.deleteById(id);
    	
    	return "deleted Successful";
     }
}
