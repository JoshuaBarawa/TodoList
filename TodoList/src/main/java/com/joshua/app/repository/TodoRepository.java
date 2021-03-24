package com.joshua.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.joshua.app.model.Todo;


@Transactional(readOnly = true)
public interface TodoRepository extends CrudRepository<Todo, Long>{
	
	List<Todo> findAllByUserId(Long id);
	
}
