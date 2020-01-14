<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
<script src="<c:url value="/resource/js/reg.js"/>" type="text/javascript"></script>
<title>Registration form</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div>
		<h1>Register Here !</h1>
		<div>
			<div>
				<c:url value="/registration" var="url"></c:url>
				<form:form method="post" action="${url}" commandName="user">
					<div>
						<div>
							<div>
								<h3> User Details </h3>
							</div>
                            <div>
								<form:label path="userName">UserName</form:label>
								<form:input type="text" placeholder="Enter Username.."
									path="userName" id="username"></form:input>
								<form:errors path="userName"></form:errors>
							</div>
							<div>
								<form:label path="firstName">First Name</form:label>
								<form:input type="text" placeholder="Enter First Name.."
									path="firstName"></form:input>
								<form:errors path="firstName" id="firstName"></form:errors>
							</div>
							<div>
								<form:label path="lastName">Last Name</form:label>
								<form:input type="text" placeholder="Enter Last Name.."
									path="lastName" id="lastName"></form:input>
							</div>
						</div>
						<div>
							<div>
								<form:label path="customerPhone">Phone Number</form:label>
								<form:input type="number" placeholder="Enter Phone Number.."
									path="customerPhone" id="phone"></form:input>
							</div>
						</div>
						<div>
							<form:label path="shippingAddress">Address</form:label>
							<form:textarea type="text" placeholder="Enter Shipping Address.."
								path="shippingAddress" id="address"></form:textarea>
						</div>
						<div>
							<div>
								<form:label path="password">Password</form:label>
								<form:input type="password" placeholder="********"
									path="password" id="pass"></form:input>
							</div>
							<div>
								<label>Confirm Password</label> <input type="password"
									placeholder="********" id="confirmpass" />
							</div>
						</div>

						<div>
							<button type="submit"
								id="check">Submit</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>