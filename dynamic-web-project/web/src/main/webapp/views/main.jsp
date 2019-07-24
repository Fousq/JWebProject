<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : fn:substring(pageContext.request.locale, 0, 2)}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head lang="${language}">
<meta>
<title>Insert title here</title>
</head>
<body>
	hi, ${username}
	<a href="main?action=logout"><fmt:message key="label.text.logout" /></a>
</body>
</html>