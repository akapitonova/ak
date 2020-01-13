<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<c:if test="${not empty payOrderSuccess}">
    	<div style="color: #ff0000;">${payOrderSuccess}</div>
    </c:if>
	<h3>Catalog Page</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Name</th>
			<th>Photo</th>
			<th>Price</th>
			<th>Store quantity</th>
			<th>Buy</th>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.name}</td>
				<td><img src="<c:url value="/resource/static/${product.photo}"/>" width="50"></td>
				<td>${product.price }</td>
				<td>${product.storeqty }</td>
				<td align="center">
					<a href="<c:url value="/buy/${product.id}"/>">Buy</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>