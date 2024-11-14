package com.springboot.restapi.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	User findByUsername(String username);
	
	@Query("SELECT u FROM User u ORDER BY u.username")
	List<User> findAllByUsername();

	 //Method to find users with pagination
	 Page<User> findAll(Pageable pageable);
}
