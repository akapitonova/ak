package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.CustomerOrder;
import com.accenture.flowershop.back.entity.OrderItem;

import java.util.List;

public interface CustomerOrderDao {

	void addCustomerOrder(CustomerOrder customerOrder);

	List<CustomerOrder> getCustomerOrders();

	List<CustomerOrder> getCustomerOrdersForUser(String userId);

	CustomerOrder getCustomerOrderById(String orderId);

	CustomerOrder findCustomerOrder(String orderId);

	void updateCustomerOrder(CustomerOrder order);
}
