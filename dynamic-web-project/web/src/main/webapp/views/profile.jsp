<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html language="${language}">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li><a href="${pageContext.request.requestURI}?locale=en"><fmt:message key="label.lang.en" /></a></li>
		<li><a href="${pageContext.request.requestURI}?locale=ru"><fmt:message key="label.lang.ru" /></a></li>
	</ul>
	<hr>
		<fmt:message key="label.context.id"/>: ${sessionScope.id}<br>
		<fmt:message key="label.context.username" />: ${username}<br>
		<fmt:message key="label.context.telephone" />: ${telephone}<br>
		<fmt:message key="label.context.country" />: ${country}<br>
		<fmt:message key="label.context.birthday" />: ${ birthday}<br>
	<hr>
	<br/>
	<a href="advert"><fmt:message key="label.context.advert" /></a>
	<a href="edit"><fmt:message key="label.text.edit" /></a>
	<a href="manage"><fmt:message key="label.text.manage" /></a>
</body>
</html>