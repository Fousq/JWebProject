<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="advert" method="post">
		<input type="hidden" name="action" value="createItem" />
		<label for="name">Name: </label>
		<input type="text" name="name" id="name" /><br>
		<label for="description">Description</label>
		<textarea rows="10" cols="50" name="description" id="description">	
		</textarea><br>
		<label for="price">Price: </label>
		<input type="number" name="price" id="price" /><br>
		<label for="category">Category: </label>
		<select name="category" id="category">
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}"><c:out value="${category.name}" /></option>
			</c:forEach>
		</select>
		<input type="submit" value="CREATE" />
	</form>
</body>
</html>