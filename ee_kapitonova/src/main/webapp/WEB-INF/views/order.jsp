<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<h3>Order <c:out value = "${order.id}"/></h3>

	<c:if test="${not empty notHaveFounds}">
    	<div style="color: #ff0000;">${notHaveFounds}</div>
    </c:if>

    <h4>Info about shipping</h4>
    <div>
        Recipient: <c:out value = "${user.firstName} ${user.lastName}"/>
        <br>Phone: <c:out value = "${user.customerPhone}"/>
        <br>Shipping adderss: <c:out value = "${user.shippingAddress}"/>
    </div>

    <h4>Info about purchase</h4>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		<c:forEach var="item" items="${order.orderItems}">
			<tr>
				<td>${item.productName}</td>
				<td>${item.price}</td>
				<td>${item.quantity}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
	On your balance: <c:out value = "${user.balance}"/>
	<c:if test="${not empty user.discount}">
	You have a discount <c:out value = "${user.discount}"/>%.
	<br>Your purchase will be made subject to the available discount.
	</c:if>
	</div>
    <div>
		<c:url value="/order/pay/${order.id}" var="url"></c:url>
		<a href="${url}">Pay Order</a>
	</div>
	<div>
    		<c:url value="/order/cancel/${order.id}" var="url"></c:url>
    		<a href="${url}">Cancel Order</a>
    	</div>
</body>
</html>