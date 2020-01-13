package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.CustomerOrder;
import com.accenture.flowershop.back.entity.OrderItem;

import java.util.List;

public interface CustomerOrderService {

	void addCustomerOrder(CustomerOrder customerOrder);
	void closeCustomerOrder(String orderId);
	List<CustomerOrder> getCustomerOrders();
	CustomerOrder getCustomerOrderById(String orderId);
	void payCustomerOrder(String orderId);
	List<CustomerOrder> getCustomerOrdersForUser(String userId);
    void cancelOrder(String orderId);
}
