package org.example.again.repositories;

import java.util.Optional;

import org.example.again.Entities.Like;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Integer>{
	Optional<Like> findLikeByPostAndUser(Post post , User user);

}
