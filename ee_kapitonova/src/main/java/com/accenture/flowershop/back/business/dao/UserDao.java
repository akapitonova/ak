package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.Users;

import java.util.List;

public interface UserDao {

	List<Users> getAllUsers();

	void deleteUser(String userId);
	
	void addUser(Users user);
	
	Users getUserById(String userId);

	Users getUserByUsername(String username);

	void updateUser(Users user);
}
