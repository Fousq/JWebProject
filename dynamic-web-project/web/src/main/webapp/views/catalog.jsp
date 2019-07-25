<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="catalog" method="GET">
		<input type="hidden" value="search"/>
		<select name="categoryType">
			<option value="0">All</option>
			<c:forEach var="category" items="${categories}">
				<option value="${category.getId()}"><c:out value="${category.getName()}" /></option>
			</c:forEach>
		</select>
		<input type="submit" value="search"/>
	</form>
</body>
</html>