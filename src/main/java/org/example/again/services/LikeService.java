package org.example.again.services;

import org.example.again.Entities.Like;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.example.again.repositories.LikeRepository;
import org.example.again.repositories.PostRepository;
import org.example.again.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PostRepository postRepository;
	
	public void setLike(int postid,String likerName) {
	    User liker = userRepository.findByusername(likerName) ;
	    Post post = postRepository.findPostById(postid);
	    likeRepository.save(new Like(post,liker));
	}
	public void unsetLike(int favId) {
        Like like = likeRepository.findById(favId).orElse(null);
        likeRepository.delete(like);

    }
 
	
	
}
