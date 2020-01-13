package com.accenture.flowershop.back.entity;

import com.accenture.flowershop.front.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "userid")
	private Long userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "customerphone")
	private String customerPhone;

	@Column(name = "shippingaddress")
	private String shippingAddress;

	@Column(name = "balance")
	private BigDecimal balance;

	@Column(name = "discount")
	private BigDecimal discount;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CustomerOrder> customerOrders;

	public Long getId() {
		return userId;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
