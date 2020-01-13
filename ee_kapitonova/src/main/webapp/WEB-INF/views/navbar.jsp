<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/navbar.css"/>">
</head>
<%
String userName=(String)session.getAttribute("userName");
%>
<body>
	<div class="navbar">
	    <div>
	    <img src="<c:url value="/resource/static/logo.jpg"/>"
        				href="<c:url value="/catalog"/>" width="100px" height="100px"
        				alt="logo-image"></img>
	    </div>
		<div id="myNavbar">
				<div class="nav-item"><a href=" <c:url value="/catalog" />">Catalog</a></div>
				<%if (userName != null) { %>
					<div class="nav-item"><a href="<c:url value="/userinfo" />">Welcome <%= userName%></a></div>
					<div class="nav-item"><a href="<c:url value="/cart" />"> Cart</a></div>
					<div class="nav-item"><a href="<c:url value="/logout" />"> Logout</a></div>
				<%}else { %>
					<div class="nav-item"><a href="<c:url value="/login" />"> Cart</a></div>
					<div class="nav-item"><a href="<c:url value="/register" />"> SignUp</a></div>
					<div class="nav-item"><a href="<c:url value="/login" />"> Login</a></div>
				<%} %>
		</div>
	</div>
</body>
</html>