package com.joshua.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.joshua.app.model.User;
import com.joshua.app.model.UserRole;



@Service
public class UserService {
	
	@Autowired
	private UserRegistration userRegistration;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void signUpUser(UserRequest request) {
		userRegistration.registerUser(new User(
				request.getUserName(),
				request.getEmail(),
				passwordEncoder.encode(request.getPassword()),
				UserRole.USER
				));
	}
	
	public void confirmToken(String token) {
		userRegistration.confirmToken(token);
	}
	
	
	
}
