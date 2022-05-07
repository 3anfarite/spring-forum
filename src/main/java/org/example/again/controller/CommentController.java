package org.example.again.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.example.again.Entities.Comment;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.CommentRepository;
import org.example.again.repositories.PostRepository;
import org.example.again.repositories.UserRepository;
import org.example.again.services.CommentService;
import org.example.again.services.PostService;
import org.example.again.services.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentController {

	@Autowired
	private CommentRepository  commentRepository;

	@Autowired
	private PostRepository  postRepository;

	@Autowired
	private UserRepository  userRepository;

	@Autowired
	private UserServiceimpl userService;

	@Autowired
	private CommentService commentService;


	@PostMapping("/show")
	public View addComment(@RequestParam("reply") String reply, User user, 
			@RequestParam("id_post") int id_post,Principal princ,
			HttpServletRequest request) {
		Comment comment = new Comment();
		comment.setReply(reply);
		comment.setPost(postRepository.findPostById(id_post));
		String username=princ.getName();
		user = userService.findUserByUserName(username);
		commentService.save(comment,user);
//		String contextPath = request.getContextPath();
		return new RedirectView("/");
	} 




}
