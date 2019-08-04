<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
		<li><a href="${pageContext.request.requestURI}?locale=en&selected=${selected}&page=${pageId}"><fmt:message key="label.lang.en" /></a></li>
		<li><a href="${pageContext.request.requestURI}?locale=ru&selected=${selected}&page=${pageId}"><fmt:message key="label.lang.ru" /></a></li>
	</ul>
	<br>
	<form action="catalog" method="post">
		<select name="category">
			<option value="0">--Choose category--</option>
			<c:forEach var="category" items="${categories}">
				<c:choose>
					<c:when test="${category.getId() == selected}">
						<option value="${category.getId()}" selected ><c:out value="${category.getName()}" /></option>
					</c:when>
					<c:otherwise>
						<option value="${category.getId()}"><c:out value="${category.getName()}" /></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<input type="submit" value="search"/>
	</form>
	<c:if test="${not empty param.selected and param.selected != 0 }">
		<c:choose>
			<c:when test="${records.size() != 0}">
				<c:forEach var="i" begin="0" end="${records.size() - 1 }">
				<c:set var="record" value="${records.get(i) }" />
				<c:set var="item" value="${items.get(i) }"/>
				<a href="${item.id}"><c:out value="${item.name}"/></a>
				<span><c:out value="${record.createdAt}"/></span>
				<span><c:out value="${item.price }"/></span>
				<span><c:out value="${item.description}"/></span>
			</c:forEach>
			<c:choose>
			<c:when test="${previous}">
				<a href="${pageContext.request.requestURI}?selected=${param.selected}&page=${pageId - 1}"><fmt:message key="label.text.previous" /></a>
			</c:when>
			<c:otherwise>
				<span><fmt:message key="label.text.previous" /></span>
			</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${next}">
					<a href="${pageContext.request.requestURI}?selected=${param.selected}&page=${pageId + 1}"><fmt:message key="label.text.next" /></a>
				</c:when>
				<c:otherwise>
					<span><fmt:message key="label.text.next" /></span>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<span>NO ITEM</span>
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>