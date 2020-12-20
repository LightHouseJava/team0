package com.lighthouse.team0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lighthouse.team0.entity.User;
import com.lighthouse.team0.mapper.UserMapper;

@Controller
public class RegisterController {
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registSubmission(@ModelAttribute("newUser") User newUser, Model model) {
		boolean userExists = userMapper.checkUserExists(newUser.getUserName());
		model.addAttribute("userExists", userExists);
		if(userExists) {
			model.addAttribute("userNameExistsMessage", "User name has been used, please login this one or register a new one.");
			return "register";
		}else {
			userMapper.insertNewUser(newUser);
			model.addAttribute("registerFinishedMessage", "You have registered successfully! Now you can sign in.");
			return "register";
		}
	}
}
