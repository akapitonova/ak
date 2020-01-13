package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.CustomerOrder;
import com.accenture.flowershop.back.entity.OrderItem;
import com.accenture.flowershop.front.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@EnableTransactionManagement
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	public void addCustomerOrder(CustomerOrder customerOrder) {
		em.persist(customerOrder);
	}

	public List<CustomerOrder> getCustomerOrders() {
		List<CustomerOrder> customerOrders = new ArrayList<>();
		try{
			customerOrders = em.createQuery("SELECT t FROM " + CustomerOrder.class.getName() + " t WHERE t.status IN (:statusList)")
					.setParameter("statusList", Arrays.asList(Status.CREATED, Status.PAYED))
					.getResultList();
		} catch (Exception e) {
			logger.error("Get customer orders throw some error: " + e);
		}
		return customerOrders;
	}

	public List<CustomerOrder> getCustomerOrdersForUser(String userId) {
		List<CustomerOrder> customerOrders = new ArrayList<>();
		try {
			customerOrders = em.createQuery("SELECT t FROM " + CustomerOrder.class.getName() + " t WHERE t.status = :status"
					+" AND t.user.userId=:userId")
					.setParameter("userId", Long.parseLong(userId))
					.setParameter("status", Status.CREATED)
					.getResultList();
		} catch (Exception e) {
			logger.error("Get customer orders for user " + userId + " throw some error: " + e);
		}
		return customerOrders;
	}

	public CustomerOrder getCustomerOrderById(String orderId) {
		CustomerOrder customerOrder = em.find(CustomerOrder.class, Long.parseLong(orderId));
		return customerOrder;
	}

	public CustomerOrder findCustomerOrder(String orderId) {
		return em.find(CustomerOrder.class, Long.parseLong(orderId));
	}

	public void updateCustomerOrder(CustomerOrder order) {
		em.merge(order);
	}
}
