package org.example.again.repositories;

import java.util.Optional;

import org.example.again.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
    User findByusername(String username);
    User getUserById(int IdUser);
    Optional<User> findByUsername(String username);
}
