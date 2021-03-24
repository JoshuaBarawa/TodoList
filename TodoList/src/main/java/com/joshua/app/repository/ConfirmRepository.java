package com.joshua.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.joshua.app.model.ConfirmationToken;

@Transactional(readOnly = true)
public interface ConfirmRepository extends CrudRepository<ConfirmationToken, Long>{

	ConfirmationToken findByToken(String token);

}
