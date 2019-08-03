<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<c:set var="pageId" value="${param.page}" />
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
	<c:forEach var="i" begin="0" end="${records.size() - 1}">
		<hr>
			<c:set var="record" value="${records.get(i) }"/>
			<c:set var="item" value="${items.get(i) }"/>
			<a href="#"><c:out value="${item.name}"/></a><br>
			<span><fmt:message key="label.context.status" />: 
			<fmt:message key="${record.active ? 'label.context.activated' : 'label.context.deactivated' }" />
			</span><br>
			<span><fmt:message key="label.context.createdAt" />: <c:out value="${record.createdAt}" /></span><br>
			<a href="delete?item=${item.id}"><fmt:message key="label.text.delete" /></a>
		<hr>
	</c:forEach>
	<c:choose>
		<c:when test="${previous}">
			<a href="${pageContext.request.requestURI}?page=${pageId - 1}"><fmt:message key="label.text.previous" /></a>
		</c:when>
		<c:otherwise>
			<span><fmt:message key="label.text.previous" /></span>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${next}">
			<a href="${pageContext.request.requestURI}?page=${pageId + 1}"><fmt:message key="label.text.next" /></a>
		</c:when>
		<c:otherwise>
			<span><fmt:message key="label.text.next" /></span>
		</c:otherwise>
	</c:choose>
</body>
</html>