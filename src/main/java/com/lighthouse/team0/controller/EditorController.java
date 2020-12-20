package com.lighthouse.team0.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lighthouse.team0.entity.Blog;
import com.lighthouse.team0.mapper.BlogMapper;
import com.lighthouse.team0.mapper.ImageMapper;

@Controller
public class EditorController {
	@Autowired
	BlogMapper blogMapper;
	
//	@PostMapping(value = "/image")
//	public String uploadImage(@RequestParam("image") MultipartFile imgFile, Model model) throws IOException {
//		//TODO: process POST request
//		byte[] img = imgFile.getBytes();
//		imageMapper.saveImage(img);
//		
//		byte[] firstImg = imageMapper.getAllImages().get(0);
//
//		String encodedImage = Base64.getEncoder().encodeToString(firstImg);
//
//		model.addAttribute("image1", encodedImage);
//		return "failed_page";
//	}
	
	
	@PostMapping("/newBlogSubmission")
	public String newBlogSubmission(@RequestParam("username") String userName, @ModelAttribute("blog") Blog blog,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		blog.setUserName(userName);
		blogMapper.addBlog(blog);
		
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT); // post redirect
		redirectAttributes.addAttribute("userName", userName);
		return "redirect:/mypage";
	}
	
	@PostMapping("/editor")
	public String editor(@RequestParam("username") String userName, Model model) {
		model.addAttribute("blog", new Blog());
		model.addAttribute("username", userName);	
		return "editor";
	}
	
	

}
