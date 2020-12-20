package com.lighthouse.team0.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lighthouse.team0.entity.User;
import com.lighthouse.team0.mapper.UserMapper;

@Controller
public class LoginController {
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("loginUser", new User());
		return "login";
	}
	
	@PostMapping("/loginSubmission")
	public String loginSubmisstion(@ModelAttribute("loginUser") User loginUser, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT); // post redirect
		if(userMapper.checkUserExists(loginUser.getUserName())) {
			User tmpUser = userMapper.findByUserName(loginUser.getUserName());
			if(tmpUser.getPassword().equals(loginUser.getPassword())) {
				redirectAttributes.addAttribute("userName", loginUser.getUserName());
				return "redirect:/mypage";
			}
			else
				return "redirect:/failed_page";
		}else
			return "redirect:/failed_page";
	}
	
	@PostMapping("/failed_page")
	public String failed_page() {
		return "failed_page";
	}
	
		
}
