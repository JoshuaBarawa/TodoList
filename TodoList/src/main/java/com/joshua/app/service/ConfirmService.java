package com.joshua.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshua.app.model.ConfirmationToken;
import com.joshua.app.repository.ConfirmRepository;

@Service
public class ConfirmService {
	
	@Autowired
	private ConfirmRepository confirmRepository;
	
	public void saveToken(ConfirmationToken token) {
		confirmRepository.save(token);
	}
	
	public ConfirmationToken confirmToken(String token) {
	 ConfirmationToken confirmToken = confirmRepository.findByToken(token);
		confirmToken.setConfirmedAt(LocalDateTime.now());
		
		return confirmToken;
		
	}
	
	public ConfirmationToken getToken(String token) {
		return confirmRepository.findByToken(token);
	}

}
