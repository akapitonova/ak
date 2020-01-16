package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Cart;
import com.accenture.flowershop.back.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

	void addCustomerOrder(CustomerOrder customerOrder);
	CustomerOrder createCustomerAndAddOrder(Cart cart, String userName);
	void closeCustomerOrder(String orderId);
	List<CustomerOrder> getCustomerOrders();
	CustomerOrder getCustomerOrderById(String orderId);
	void payCustomerOrder(String orderId);
	List<CustomerOrder> getCustomerOrdersForUser(String userId);
    void cancelOrder(String orderId);
}
