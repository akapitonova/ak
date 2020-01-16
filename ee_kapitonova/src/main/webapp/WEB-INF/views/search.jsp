<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
<script src="<c:url value="/resource/js/catalog.js"/>" type="text/javascript"></script>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<form action="<c:url value="/search/"/>">
         <p><input class="search-input" type="search" name="searchParam" placeholder="Enter product name">
          Select price range <input class="range" type="number" name="minParam">
          <input class="range" type="number" name="maxParam">
          <input type="submit" value="Search"></p>
     </form>
    <c:choose>
    <c:when test="${not empty products}">
	<h3> <c:out value="${results}"/></h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Name</th>
			<th>Photo</th>
			<th>Price</th>
			<th>Store quantity</th>
			<th>Quantity</th>
			<th>Buy</th>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr class="item">
				<td>${product.name}</td>
				<td><img src="<c:url value="/resource/static/${product.photo}"/>" width="50"></td>
				<td>${product.price }</td>
				<td >${product.storeqty }</td>
				<td>
				    <input class="qty-input" name="<c:out value="${product.productId}"/>" data-qty="${product.storeqty}"
				    type="number" min="0" max="<c:out value="${product.storeqty}"/>" value="1" width="50px">
				</td>
				<td align="center">
				    <button id="<c:out value="${product.productId}"/>">Buy</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:when test="${not empty noResults}">
	    <h3> <c:out value="${noResults}"/></h3>
	</c:when>
    	<c:otherwise>
    	    <h3> No search results</h3>
    	</c:otherwise>
    </c:choose>
</body>
</html>