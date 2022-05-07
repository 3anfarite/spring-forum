package org.example.again.controller;
import org.springframework.web.servlet.View;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.again.Entities.Pic;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.Entities.Comment;
import org.example.again.repositories.CommentRepository;
import org.example.again.repositories.PicRepository;
import org.example.again.repositories.PostRepository;
import org.example.again.repositories.UserRepository;
import org.example.again.services.CommentService;
import org.example.again.services.PicStorageService;
import org.example.again.services.PostService;
import org.example.again.services.UserServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PicController {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PicRepository picRepository;

	@Autowired
	private PicStorageService picStorageService;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/{id_post}")
	public String image(Model model,@PathVariable int id_post,Post post,
			@Valid @ModelAttribute("comment")Comment comment, Pageable pageable) {
		Page<Post> page = postRepository.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("pics",picRepository);
		model.addAttribute("commentRepository", commentRepository);
		return "redirect:/";
	}
	@GetMapping
	public String tikchbila(Model model,@ModelAttribute("post")Post post,
			@Valid @ModelAttribute("comment")Comment comment,
			@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC,size = 5) Pageable pageable) {
         
		Page<Post> page = postRepository.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("pics",picRepository);	
		model.addAttribute("commentRepository", commentRepository);
		return "home";
	}

	@GetMapping("/show/{fileId}")
	public void show(@PathVariable("fileId") Integer fileId,HttpServletResponse response) 
			throws IOException {

		Pic pic=picStorageService.getFile(fileId);
		response.setContentType((pic.getpicType()));
		InputStream test=new ByteArrayInputStream((pic.getData()));
		IOUtils.copy(test,response.getOutputStream());

	}



}
