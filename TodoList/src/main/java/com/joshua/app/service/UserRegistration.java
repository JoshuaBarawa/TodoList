package com.joshua.app.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joshua.app.model.ConfirmationToken;
import com.joshua.app.model.User;
import com.joshua.app.repository.UserRepository;

@Service
public class UserRegistration implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConfirmService confirmService;
	
	@Autowired
	private EmailSender emailSender;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}

	public String registerUser(User user) {

		boolean exists = userRepository.findByUserName(user.getUsername()) != null;
		if (exists) {
			return "Username already exists";
		}

		userRepository.save(user);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15), user);

		confirmService.saveToken(confirmToken);
		
		String message = "Click this link to confirm your email: " + "http://localhost:8080/api/user/confirm/" + token;
		emailSender.sendEmail(user.getEmail(), message);
		System.out.println(token);
		return token;
	}
	
     @Transactional
	public String confirmToken(String token) {

		ConfirmationToken confirmToken = confirmService.getToken(token);

		if (confirmToken.getExpiresAt().isBefore(LocalDateTime.now())) {
			return "Token already expired";
		}

		if (confirmToken.getConfirmedAt() != null) {
			return "Token was confirmed already";
		}

		confirmService.confirmToken(token);
		enableUser(confirmToken.getUserId().getEmail());

		return "Confirmed";
	}

	public boolean enableUser(String email) {
		User user = userRepository.findByEmail(email);
		user.setEnabled(true);
		
		return true;
	}

}
