package org.example.again.controller;

import java.security.Principal;
import java.util.Optional;

import org.example.again.Entities.Like;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.LikeRepository;
import org.example.again.repositories.PostRepository;
import org.example.again.services.LikeService;
import org.example.again.services.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private PostRepository postRepository;
	 
	@Autowired
	private UserServiceimpl userService;
	
	@Autowired
	private LikeRepository likeRepository;
	
	
    @PostMapping("/set/{post_id}")
	public String setLike(@RequestParam("post_id") int postid, Principal principal) throws Exception {
		String likerName = principal.getName();
		User user = userService.findUserByUserName(likerName);
		Post post = postRepository.findPostById(postid);
		Optional<Like> likeByPostAndUser = likeRepository.findLikeByPostAndUser(post , user);
		
		if (likeByPostAndUser.isPresent()) throw new Exception("You have already liked");
		 
		likeService.setLike(postid, likerName);
		return "redirect:/";
	}
  

}
 