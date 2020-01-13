package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.business.dao.UserDao;
import com.accenture.flowershop.back.entity.Users;
import com.accenture.flowershop.front.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public List<Users> getAllUsers() {
	return userDao.getAllUsers();
	}

	public void deleteUser(String userId) {
		userDao.deleteUser(userId);
	}

	@Transactional
	public void addUser(Users users){
		users.setBalance(new BigDecimal(2000.0));
		users.setDiscount(new BigDecimal(3.0));
		users.setRole(Role.USER);
		userDao.addUser(users);
	}
	
	public Users getUserById(String userId) {
		return userDao.getUserById(userId);
	}

	public Users getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	public void updateUser(Users user) {
		userDao.updateUser(user);
	}
}
