package com.lighthouse.team0.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lighthouse.team0.entity.Blog;
import com.lighthouse.team0.mapper.BlogMapper;

@Controller
public class MyPageController {
	
	@Autowired
	private BlogMapper blogMapper;
	
	@PostMapping(value = "/mypage")
	public String myPage(@RequestParam("userName") String userName, Model model) {
		System.out.println(userName);
		List<Blog> blogs = blogMapper.getBlogsByUserName(userName);
		
		model.addAttribute("blogs", blogs);
		
		return "mypage";
	}

}
