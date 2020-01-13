<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div>
        <a href="<c:url value="/logout" />"> Logout</a>
    </div>
	<div>
		<div>
			<div>
				<div>
					<h3>Add money for user <c:out value = "${user.id}"/></h3>
				</div>
				<div>
					<form name="addMoney"
						action="<c:url value="/addmoney_process"/>" method="post">
						<fieldset>
							<div>
								<input value="${user.userName}"
									name="j_username" type="text" readonly>
							</div>
							<div>
                            	<input placeholder="enter summ"
                            		name="j_summ" type="number">
                            </div>
							<div>
								<c:if test="${not empty error}">
									<div class="error" style="color: #ff0000">${error}</div>
								</c:if>
							</div>
							<div id="button">
                            	<button type="submit">Add money</button>
                            </div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>