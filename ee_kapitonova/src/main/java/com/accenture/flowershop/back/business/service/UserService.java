package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Users;
import com.accenture.flowershop.front.dto.UserDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

	List<Users> getAllUsers();
	
	void deleteUser(String userId);

	void addUser(Users users);
	
	Users getUserById(String userId);

	Users getUserByUsername(String username);

	void updateUser(Users user);

	void updateUserBalance(String username, BigDecimal summ);

	Users updateUserInfo(String userName, UserDto userDto);
}
