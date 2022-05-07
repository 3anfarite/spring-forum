package org.example.again.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.example.again.Entities.Comment;
import org.example.again.Entities.Pic;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>,
PagingAndSortingRepository <Post,Integer>{
	
    Page<Post> findTop5ByOrderByCreationDateDesc(Pageable pageable);
	List<Post> findByUser(User user);
	Post findByCommentsIn(List<Comment> comments);
	Post findPostById(int postid);
    Post findPostByPicsIn(List<Pic> pics);
    Page<Post> findAll(Pageable pageable);
    
//    @Modifying
//    @Transactional
//    @Query ("UPDATE Post p SET p.liked = :bool WHERE p.id = :id")
//    void setLikedForPost(@Param("bool") Boolean bool, @Param("id") int id);
}










