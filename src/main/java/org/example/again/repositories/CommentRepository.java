package org.example.again.repositories;


import java.util.List;

import org.example.again.Entities.Comment;
import org.example.again.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer>{

	List<Comment> findByUser(User user);
	List<Comment> findByPost(int idpost);
	List<Comment> findCommentByUser_Id(int id);
	List<Comment> findCommentByPost_Id(int id);
}
