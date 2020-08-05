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
   	
   	<fmt:message bundle="${loc}" key="local.message11" var="message1" />
   	<fmt:message bundle="${loc}" key="local.message12" var="message2" />
   	<fmt:message bundle="${loc}" key="local.message16" var="message3" />
   	<fmt:message bundle="${loc}" key="local.message17" var="message4" />
   	<fmt:message bundle="${loc}" key="local.message18" var="message5" />
   	<fmt:message bundle="${loc}" key="local.message19" var="message6" />
   	<fmt:message bundle="${loc}" key="local.message25" var="message7" />
   	<fmt:message bundle="${loc}" key="local.message21" var="message8" />
   	<fmt:message bundle="${loc}" key="local.message22" var="message9" />
   	<fmt:message bundle="${loc}" key="local.message23" var="message10" />
   	<fmt:message bundle="${loc}" key="local.message24" var="message11" />
   	
   	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
   	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
</head>
<body>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<input type="hidden" name="faculty" value="programming" />
		<input type="hidden" name="role" value="user" />
		<p><label>${message1} <br><input type="text" name="login" value="" /></label></p>
		<p><label>${message2} <br><input type="text" name="password" value="" /></label></p>
		<p><label>${message3} <br><input type="text" name="fname" value="" /></label></p>
		<p><label>${message4} <br><input type="text" name="lname" value="" /></label></p>
		<p><label>${message5} <br><input type="text" name="passport" value="" /></label></p>
		<p><label>${message6} <br><input type="text" name="firstsubject" value="" /></label></p>
		<p><label>${message7} <br><input type="text" name="secondsubject" value="" /></label></p>
		<p><label>
				<select>
				  	<option>${message8}</option>
				  	<option>${message9}</option>	
				</select></label><br>
					<input type="text" name="thirdsubject" value=""/></p>
		<p><label>${message10} <br><input type="text" name="gpa" value="" /></label></p>
		<p><input type="submit" value="${message11}" /></p>
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