<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

	<fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="resource/local" var="loc" />
   	
   	<fmt:message bundle="${loc}" key="local.message4" var="message4" />
   	<fmt:message bundle="${loc}" key="local.message5" var="message5" />
   	<fmt:message bundle="${loc}" key="local.message6" var="message6" />
   	
   	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
   	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
</head>
<body>
	<h1>Welcome To Main Page</h1>
	Hello, ${user.fName} ${user.gpa}
	
	<c:if test="${check == 0 }">
		<c:out value="${message6}"></c:out>
	</c:if>
	
	<c:if test="${check > 0 }">
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Passport</th>
				<th>GPA</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.fName}</td>
					<td>${user.lName}</td>
					<td>${user.passport}</td>
					<td>${user.gpa}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="log_out" />
		<input type="submit" value="${message4}" />
	</form>
	
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="check_result" />
		<input type="submit" value="${message5}" />
	</form>
	
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="localization" />
		<input type="hidden" name="local" value="en" />
		<input type="submit" value="${en_button}" />
	</form>
	
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="localization" />
		<input type="hidden" name="local" value="ru" />
		<input type="submit" value="${ru_button}" />
	</form>

</body>
</html>