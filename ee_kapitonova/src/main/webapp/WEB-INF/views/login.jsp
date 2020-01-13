<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div>
		<div>
			<div>
				<div>
					<h3>Sign In</h3>
				</div>
				<div>
					<c:if test="${not empty registrationSuccess}">
						<div style="color: #ff0000;">${registrationSuccess}</div>
					</c:if>

					<c:if test="${not empty logout}">
						<div style="color: #ff0000;">${logout}</div>
					</c:if>

					<form name="loginForm"
						action="<c:url value="/login_process"/>" method="post">
						<fieldset>
							<div>
								<input placeholder="Login"
									name="j_username" type="text">
							</div>
							<div>
								<input placeholder="Password"
									name="j_password" type="password">
							</div>
							<div>
								<c:if test="${not empty error}">
									<div class="error" style="color: #ff0000">${error}</div>
								</c:if>
							</div>
							<div id="button">
								<button type="submit">Login</button>
								<a href="<c:url value="/register"/>">Register</a>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>