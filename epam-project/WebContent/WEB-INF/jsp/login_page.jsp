<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

	<style type="text/css">
		<%@include file="/WEB-INF/css/style.css"%>
	</style>

	<fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="resource/local" var="loc" />
   	
   	<fmt:message bundle="${loc}" key="local.message11" var="message1" />
   	<fmt:message bundle="${loc}" key="local.message12" var="message2" />
   	<fmt:message bundle="${loc}" key="local.message13" var="message3" />
   	<fmt:message bundle="${loc}" key="local.message14" var="message4" />
   	<fmt:message bundle="${loc}" key="local.message15" var="message5" />
   	
   	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
   	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
</head>
<body>
	<div id="wrapper">
	
		<div id="header">
			<h1>GSTU</h1>
			
			<div id="nav">
				<ul>
					<li>Main</li>
					<li>About</li>
					<li>Contacts</li>
				</ul>
			</div>
		</div>
		
		<div class="clear"></div>
		
		<div id="content">
			<div id="container">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="authorization" />
					<label>${message1} <input type="text" name="login" value="" /></label>
					<label>${message2} <input type="password" name="password" value="" /></label>
					<input type="submit" value="${message3}" />
					
					<c:if test="${error_aut != null }">
						<c:out value="${error_aut}"></c:out>
					</c:if>
				</form>
			
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="go_to_economics_registration" />
					<input type="submit" value="${message4}" />
				</form>
			
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="go_to_programming_registration" />
					<input type="submit" value="${message5}" />
				</form>
			</div>
		</div>
		
		<div id="footer">
			<div id="lang">
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
			</div>
		</div>
	</div>
</body>
</html>