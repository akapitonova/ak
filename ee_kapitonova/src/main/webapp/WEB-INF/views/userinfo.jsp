<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
<script src="/resource/js/userinfo.js" type="text/javascript"></script>
<title>User Info <c:out value = "${user.userName}"/> </title>
</head>
<body>
<%@ include file="navbar.jsp"%>
	<div>
        <c:url value="/change_user_info" var="url"></c:url>
        				<form:form method="post" action="${url}" commandName="user">
        					<div>
        						<div>
        							<div>
        								<h3> User Details <c:out value = "${user.userName}"/></h3>
        							</div>
        								<div>
                                    	On your balance: <c:out value = "${user.balance}"/>
                                    	<c:if test="${not empty user.discount}">
                                    	You have a discount <c:out value = "${user.discount}"/>%.
                                    	<br>Your purchase will be made subject to the available discount.
                                    	</c:if>
                                    	</div>
        							<div>
        								<form:label path="firstName">First Name</form:label>
        								<form:input type="text" value="${user.firstName}"
        									path="firstName" id="firstName"></form:input>
        							</div>
        							<div>
        								<form:label path="lastName">Last Name</form:label>
        								<form:input type="text" value="${user.lastName}"
        									path="lastName" id="lastName"></form:input>
        							</div>
        						</div>
        						<div>
        							<div>
        								<form:label path="customerPhone">Phone Number</form:label>
        								<form:input type="number" value="${user.customerPhone}"
        									path="customerPhone" id="phone"></form:input>
        							</div>
        						</div>
        						<div>
        							<form:label path="shippingAddress">Address</form:label>
        							<form:textarea type="text" value="${user.shippingAddress}"
        								path="shippingAddress" id="address"></form:textarea>
        						</div>
        						<div>
        							<button type="submit"
        								onclick="check">Change info</button>
        						</div>
        					</div>
        				</form:form>
	</div>
	<c:if test="${not empty orders}">
	<div>
	<h4>Info about unpayed orders</h4>
        <div>
            	<table cellpadding="2" cellspacing="2" border="1">
            		<tr>
            		    <th>Order Id</th>
            			<th>Total</th>
            			<th>Show details</th>
            		</tr>
            		<c:forEach var="order" items="${orders}">
            		    <tr>
            		        <td>${order.id}</td>
            		        <td>${order.total}</td>
            		        <td><a href="<c:url value="/order/${order.id}"/>">Show details</a></td>
            		    </tr>
            		</c:forEach>
            	</table>
        </div>
	</div>
	</c:if>
</body>
</html>