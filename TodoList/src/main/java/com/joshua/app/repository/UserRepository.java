package com.joshua.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.joshua.app.model.User;


@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long>{
	

	User findByEmail(String email);
	User findByUserName(String name);
}
