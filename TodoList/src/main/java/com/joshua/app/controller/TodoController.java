package com.joshua.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshua.app.model.Todo;
import com.joshua.app.model.User;
import com.joshua.app.service.TodoService;

@RestController
@RequestMapping(path = "/api/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/home")
	public String addTodo() {
		return "Login Succesfuly";
	}
	
	
	@PostMapping("/addtodo")
	public String addTodo(@RequestBody Todo todo) {
		User user =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = user.getId();
		todoService.addTodo(new Todo(
				todo.getName(),
				todo.getDescription(),
				todo.getStatus(),
				id
				));
		return "Added Succesfuly";
	}
	
	@GetMapping("/todos")
	public List<Todo> getTodos(Todo todo, Long id){
		User user =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		id = user.getId();
		todo.setUserId(id);
		return todoService.getTodos(todo.getUserId());
	}
	
	@PutMapping("/etodo/{id}")
	public void updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
		todoService.updateTodo(id, todo);
	}
	
	@DeleteMapping("/dtodo/{id}")
	public void deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
	}
	
}
