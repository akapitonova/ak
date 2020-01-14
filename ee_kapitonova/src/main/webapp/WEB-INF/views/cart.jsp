<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div>
		<div>
		<c:choose>
		<c:when test="${not empty cart.cartItems}">
			<div>
				<br> List of Products Purchased
				<div>
					<a href="<c:url value="/cart/removeAllItems"/>" >Clear Cart </a>
				</div>
				<div>
					<c:url value="/order" var="url1"></c:url>
					<a href="${url1}">Check Out </a>
				</div>
				<table id="productList" cellpadding="2" cellspacing="2" border="1">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Total Price</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="cartItem" items="${cart.cartItems}">
						<tr>
							<td>${cartItem.productName}</td>
							<td>${cartItem.quantity}</td>
							<td>${cartItem.price}</td>
							<td>${cartItem.price * cartItem.quantity}</td>
							<td><a href="<c:url value="/cart/removeCartItem/${cartItem.productId}"/>">remove</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				Total Price: ${cart.totalPrice}
			</div>
			</c:when>
			<c:otherwise>
			    <h3> Your cart is empty!</h3>
			</c:otherwise>
			</c:choose>
			<c:url value="/catalog" var="url"></c:url>
			<a href="${url}">Continue Shopping</a>
		</div>
	</div>
</body>
</html>