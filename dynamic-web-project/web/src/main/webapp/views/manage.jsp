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
	<br>
	<c:forEach var="i" begin="0" end="${records.size() - 1}">
		<hr>
			<c:set var="record" value="${records.get(i) }"/>
			<c:set var="item" value="${items.get(i) }"/>
			<a href="#"><c:out value="${item.name}"/></a><br>
			<span><fmt:message key="label.context.status" />: <c:out value="${record.active ? 'Activated' : 'Deactivated'}" /></span><br>
			<span><fmt:message key="label.context.createdAt" />: <c:out value="${record.createdAt}" /></span>
		<hr>
	</c:forEach>
</body>
</html>