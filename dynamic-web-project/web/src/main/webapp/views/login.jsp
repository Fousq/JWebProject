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
<title>Insert title here</title>
</head>
<body>
	<c:out value="${language}" />
	<ul>
		<li><a href="${pageContext.request.requestURI}?locale=en"><fmt:message key="label.lang.en" /></a></li>
		<li><a href="${pageContext.request.requestURI}?locale=ru"><fmt:message key="label.lang.ru" /></a></li>
	</ul>
	<br/>
	<form action="login" method="post">
		<label for="login"><fmt:message key="label.context.login" /></label>
		<input type="text" name="login" id="login" required />
		<label for="password"><fmt:message key="label.context.password" /></label>
		<input type="password" name="password" id="password" required />
		<label for="togglePassword"><fmt:message key="label.context.togglePassword" /></label>
		<input type="checkbox" id="togglePassword" />
		<fmt:message key="label.text.login" var="loginSubmit"/>
		<input type="submit" value="${loginSubmit}" />
	</form>
	<c:if test="${not empty errorMessage}">
		<fmt:message key="${errorMessage}"/>
	</c:if>
	<a href="registration"><fmt:message key="label.text.registration"/></a>
	<script type="text/javascript" src="../static/refreshSecurity.js"></script>
	<script type="text/javascript" src="../static/toggle.js">
	</script>
</body>
</html>