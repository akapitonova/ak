package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.business.dao.CustomerOrderDao;
import com.accenture.flowershop.back.entity.CustomerOrder;
import com.accenture.flowershop.front.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderDao.addCustomerOrder(customerOrder);
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
