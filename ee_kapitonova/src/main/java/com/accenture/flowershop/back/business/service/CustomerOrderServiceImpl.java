package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.business.dao.CustomerOrderDao;
import com.accenture.flowershop.back.entity.*;
import com.accenture.flowershop.front.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;

	@Autowired
	private UserService userService;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderDao.addCustomerOrder(customerOrder);
	}

	public CustomerOrder createCustomerAndAddOrder(Cart cart, String userName) {
		Users user = userService.getUserByUsername(userName);
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setUser(user);
		customerOrder.setStatus(Status.CREATED);
		customerOrder.setOpenDate(new Date());
		customerOrder.setTotal(cart.getTotalPrice());
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProductid(cartItem.getProductId());
			orderItem.setProductName(cartItem.getProductName());
			orderItem.setPrice(cartItem.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setCustomerOrder(customerOrder);
			orderItems.add(orderItem);
		}
		customerOrder.setOrderItems(orderItems);
		addCustomerOrder(customerOrder);
		return customerOrder;
	}

	public List<CustomerOrder> getCustomerOrders() {
		return customerOrderDao.getCustomerOrders();
	}

	public void closeCustomerOrder(String orderId) {
		CustomerOrder order = customerOrderDao.findCustomerOrder(orderId);
		order.setStatus(Status.CLOSED);
		order.setCloseDate(new Date());
		customerOrderDao.updateCustomerOrder(order);
	}

	public CustomerOrder getCustomerOrderById(String orderId) {
		return customerOrderDao.getCustomerOrderById(orderId);
	}

	public void payCustomerOrder(String orderId) {
		CustomerOrder order = customerOrderDao.findCustomerOrder(orderId);
		order.setStatus(Status.PAYED);
		customerOrderDao.updateCustomerOrder(order);
	}

	public List<CustomerOrder> getCustomerOrdersForUser(String userId) {
		return customerOrderDao.getCustomerOrdersForUser(userId);
	}

	public void cancelOrder(String orderId) {
		CustomerOrder order = customerOrderDao.findCustomerOrder(orderId);
		order.setStatus(Status.CANCELED);
		order.setCloseDate(new Date());
		customerOrderDao.updateCustomerOrder(order);
	}
}
