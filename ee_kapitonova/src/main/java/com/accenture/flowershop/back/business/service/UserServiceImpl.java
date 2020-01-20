package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.business.dao.UserDao;
import com.accenture.flowershop.back.entity.Users;
import com.accenture.flowershop.front.dto.UserDto;
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
		//users.setDiscount(new BigDecimal(3.0));
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

	public void updateUserBalance(String username, BigDecimal summ) {
		Users user = getUserByUsername(username);
		user.setBalance(user.getBalance().add(summ));
		updateUser(user);
	}

	public Users updateUserInfo(String userName, UserDto userDto) {
		Users user = getUserByUsername(userName);
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setCustomerPhone(userDto.getCustomerPhone());
		user.setShippingAddress(userDto.getShippingAddress());
		updateUser(user);

		return user;
	}

	public void updateDiscountForUser(long userId, int discount) {
		Users user = getUserById(String.valueOf(userId));
		user.setDiscount(new BigDecimal(discount));
		updateUser(user);
	}
}
