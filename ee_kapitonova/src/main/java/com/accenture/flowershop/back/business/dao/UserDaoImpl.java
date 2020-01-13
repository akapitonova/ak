package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<>();
		try {
			users = em.createQuery("SELECT t FROM " + Users.class.getName() + " t").getResultList();
		} catch (Exception e) {
			logger.error("Get users throw some error: " + e);
			users = new ArrayList<>();
		}
		logger.info("Getted users: " + users);
		return users;
	}

	public void deleteUser(String userId) {
		Users user = em.find(Users.class, userId);
		em.remove(user);
	}

	public void addUser(Users user) {
		em.persist(user);
		logger.info("Added user: "+ user.getUserName());
	}

	public Users getUserById(String userId) {
		return em.find(Users.class, Long.parseLong(userId));
	}

	public Users getUserByUsername(String username) {
		Users user = new Users();
		try {
			user = (Users) em.createQuery("SELECT t FROM " + Users.class.getName() + " t WHERE t.userName = :username")
					.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			logger.error("Get user throw some error: "+e);
			user = null;
		}
		return user;
	}

	public void updateUser(Users user) {
		em.merge(user);
	}
}
