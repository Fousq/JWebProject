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
<title>Insert title here</title>
</head>
<body>
	<form action="registration" method="post">
		<input type="hidden" name="action" value="registr"/>
		<label for="login"><fmt:message key="label.context.login" /></label>
		<input type="text" name="login" id="login" required />
		<label for="password"><fmt:message key="label.context.password" /></label>
		<input type="password" name="password" id="password" required />
		<label for="togglePassword"><fmt:message key="label.context.togglePassword" /></label>
		<input type="checkbox" id="togglePassword" />
		<fmt:message key="label.text.registration" var="registrSubmit"></fmt:message>
		<input type="submit" value="${registrSubmit}" />
	</form>
	<script type="text/javascript">
		const showOrHidePassword = () => {
			const password = document.getElementById('password');
			if (password.type === 'password') {
				password.type = 'text';
			} else {
				password.type = 'password';
			}
		};
		const togglePassword = document.getElementById('togglePassword');
		togglePassword.addEventListener("change", showOrHidePassword);
	</script>
</body>
</html>