package org.example.again.services;

import java.util.List;

import org.example.again.Entities.Comment;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment save(Comment comment ,User user) {
		comment.setUser(user);
		return commentRepository.save(comment);
	}
	public List <Comment> finduserComment(User user) {
		  return commentRepository.findByUser(user);
	}
	public List <Comment> getComments(){
		return (List<Comment>) commentRepository.findAll();
	}
	 
}
