package com.accenture.flowershop.back.entity;

import com.accenture.flowershop.front.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customerorder")
public class CustomerOrder implements Serializable {
	private static final long serialVersionUID = -6571020025726257848L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerorderid")
	private Long customerOrderId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "opendate")
	private Date openDate;

	@Column(name = "closedate")
	private Date closeDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;

	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<OrderItem> orderItems;

	public Long getId() {
		return customerOrderId;
	}

	public void setId(Long customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
}
