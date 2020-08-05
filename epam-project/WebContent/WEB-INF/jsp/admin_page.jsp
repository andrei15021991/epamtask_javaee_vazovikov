<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome To Admin Page</h1>
	
	<form action="Controller" method="post">
		<input type="hidden" name="currentPage" value="1" />
		<input type="hidden" name="command" value="pagination" />
		 <label for="records">Select records per page:</label>
            <select id="records" name="recordsPerPage">
                <option value="3">3</option>
                <option value="10" selected>10</option>
                <option value="15">15</option>
            </select>
        <input type="submit" value="submit" />
	</form>
	
	<c:if test="${users != null }">
		<table>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Passport</th>
				<th>Faculty</th>
				<th>GPA</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.fName}</td>
					<td>${user.lName}</td>
					<td>${user.passport}</td>
					<td>${user.faculty}</td>
					<td>${user.gpa}</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li><a>${i}(current)</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="Controller?command=pagination&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
	</c:if>
	
	<form action="Controller">
		<input type="hidden" name="command"  value="make_group"/>
		<input type="submit" value="make group" />
	</form> 

</body>
</html>