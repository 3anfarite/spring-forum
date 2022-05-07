package org.example.again.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.example.again.Entities.Comment;
import org.example.again.Entities.Pic;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.PostRepository;
import org.example.again.services.PicStorageService;
import org.example.again.services.PostService;
import org.example.again.services.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value =("/"))
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserServiceimpl userService;

	@Autowired 
	private PicStorageService picStorageService;

	@GetMapping("/home")
	public String postForm(@ModelAttribute("post") Post post,Model model) {
		return "home";
	}

	@PostMapping("/home")
	public String postFormidk(@Valid @ModelAttribute("post")Post post,BindingResult result,
			Principal principal ,Model model,@RequestParam("files") MultipartFile[] files) {	
		if(result.hasErrors()) {
			return "redirect:/";
		}
		String username=principal.getName();
		User user = userService.findUserByUserName(username);
		for(MultipartFile file : files) {
			picStorageService.store(file,user,post);
			postService.save(post,user);
		}
		return "redirect:/";	
	} 

//	@PostMapping("post/{id}")
//	public String updatePost( @RequestParam String action, @RequestParam int id_post,
//			@RequestParam(required = false) String state, HttpServletRequest request) {
//		switch (action) {
//		case "liked" :
//			postRepository.setLikedForPost(!Boolean.valueOf(state), id_post);
//			break;}
//		return "redirect:/";
//	}

}	 









