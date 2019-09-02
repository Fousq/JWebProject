<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><a href="${pageContext.request.requestURI}?locale=en"><fmt:message key="label.lang.en" /></a></li>
		<li><a href="${pageContext.request.requestURI}?locale=ru"><fmt:message key="label.lang.ru" /></a></li>
	</ul>
	<br>
	<form action="advert" method="post">
		<label for="name"><fmt:message key="label.context.itemName"/>: </label>
		<input type="text" name="name" id="name" /><br>
		<label for="description"><fmt:message key="label.context.description" />: </label>
		<textarea rows="10" cols="50" name="description" id="description">	
		</textarea><br>
		<label for="price"><fmt:message key="label.context.price" />: </label>
		<input type="number" name="price" id="price" />
		<span><fmt:message key="label.help.price" /></span><br>
		<label for="category"><fmt:message key="label.context.category" />: </label>
		<select name="category" id="category">
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}"><c:out value="${category.name}" /></option>
			</c:forEach>
		</select>
		<fmt:message var="submitCreate" key="label.text.create"/>
		<input type="submit" value="${submitCreate}" />
	</form>
	<script type="text/javascript" src="../static/refreshSecurity.js"></script>
</body>
</html>