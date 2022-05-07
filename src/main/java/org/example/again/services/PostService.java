package org.example.again.services;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.example.again.Entities.Comment;
import org.example.again.Entities.Pic;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.PicRepository;
import org.example.again.repositories.PostRepository;
import org.example.again.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService{

	@Autowired
	private PicRepository picRepository;

	@Autowired
	private UserServiceimpl userService; 

	@Autowired
	private PostRepository postRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Post post,User user) {
		post.setUser(user);
		postRepository.save(post);
	}
	public int subtractPageByOne(int page){
		return (page < 1) ? 0 : page - 1;
	}

	public Page<Post> findAllOrderedByDatePageable(int page) { 
		Pageable sortedByDate = PageRequest.of(0, 5, Sort.by("creationDate"));
		return postRepository.findTop5ByOrderByCreationDateDesc(sortedByDate); }


	public List <Post> finduserPost(User user) {
		return postRepository.findByUser(user);
	}
	public Post findPostByComments(List<Comment> comments) {
		return postRepository.findByCommentsIn(comments);
	}
	public Optional<Post> findForId(int id) {
		return postRepository.findById(id);
	}
	
	

}
