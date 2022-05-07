package org.example.again.repositories;

import java.util.List;

import org.example.again.Entities.Pic;
import org.example.again.Entities.Post;
import org.example.again.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicRepository extends JpaRepository<Pic,Integer>{ 
	Pic findPicBypicName(String picName);
	List<Pic> findByUser(User user);
	List<Pic> findPicByPost_Id(int id);

}
