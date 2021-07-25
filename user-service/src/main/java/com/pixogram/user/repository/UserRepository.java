package com.pixogram.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//public User findByUserName(String username);
	public User findByUserNameAndPassword(String userName, String password);
	
  //public User findByEmailAndPassword(String email, String password);
}
