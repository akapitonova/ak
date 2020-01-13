<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
<div>
    <a href="<c:url value="/logout" />"> Logout</a>
</div>
	<div>
	<h3> Orders Details </h3>
                <table id="orderList" cellpadding="2" cellspacing="2" border="1">
					<thead>
						<tr>
							<th>Order ID</th>
							<th>Customer</th>
							<th>Open Date</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.id}</td>
							<td>${order.user.id}</td>
							<td>${order.openDate}
							<td>${order.status}</td>
							<td>
							    <c:if test="${order.status eq 'PAYED'}">
							    <a href="<c:url value="/admin/closeOrder/${order.id}" />">Close</a>
							    </c:if>
							</td>
					</c:forEach>
					</tbody>
				</table>
	</div>
	<div>
	<h3> Users Details </h3>
	<table id="usersList" cellpadding="2" cellspacing="2" border="1">
    					<thead>
    						<tr>
    							<th>User ID</th>
    							<th>User Name</th>
    							<th>Balance</th>
    							<th>Action</th>
    						</tr>
    					</thead>
    					<tbody>
    					<c:forEach var="user" items="${users}">
    						<tr>
    							<td>${user.id}</td>
    							<td>${user.userName}</td>
    							<td>${user.balance}</td>
    							<td><a href="<c:url value="/admin/addMoney/${user.id}"/>">Add money</a></td>
    					</c:forEach>
    					</tbody>
    				</table>
	</div>
</body>
</html>