package com.joshua.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joshua.app.service.UserRequest;
import com.joshua.app.service.UserService;


@RequestMapping(path = "/api/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/signup")
	public String signUp(@RequestBody UserRequest user) {
		userService.signUpUser(user);
		
		return "A link has been sent to your email.....click to confirm Your email address";
	}

	
	@GetMapping("/confirm/{token}")
	public String confirmToken(@PathVariable String token) {
		userService.confirmToken(token);
		
		return "Confirmed";
	}
}
