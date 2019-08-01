<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
	<fmt:message key="label.welcome" />
	<ul>
		<li><a href="?locale=en"><fmt:message key="label.lang.en" /></a></li>
		<li><a href="?locale=ru"><fmt:message key="label.lang.ru" /></a></li>
	</ul>
	<c:choose>
		<c:when test="${not empty sessionScope.id}">
			<a href="profile"><fmt:message key="label.text.profile" /></a>
			<a href="logout"><fmt:message key="label.text.logout"/></a>
		</c:when>
		<c:otherwise>
			<a href="login"><fmt:message key="label.text.login" /></a>
		</c:otherwise>
	</c:choose>
</body>
</html>