package com.springboot.restapi.Service;

import com.springboot.restapi.Entity.User;

public interface UserService {

	public User saveUser(User user) ;
	public User getUserByUsername(String username) ;
}
