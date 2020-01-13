package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Users;

import java.util.List;

public interface UserService {

	List<Users> getAllUsers();
	
	void deleteUser(String userId);

	void addUser(Users users);
	
	Users getUserById(String userId);

	Users getUserByUsername(String username);

	void updateUser(Users user);
}
