<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : fn:substring(pageContext.request.locale, 0, 2)}" scope="session"/>
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
		<li><a href="?sessionLocale=en"><fmt:message key="label.lang.en" /></a></li>
		<li><a href="?sessionLocale=ru"><fmt:message key="label.lang.ru" /></a></li>
	</ul>
	<a href="login"><fmt:message key="label.text.login" /></a>
</body>
</html>