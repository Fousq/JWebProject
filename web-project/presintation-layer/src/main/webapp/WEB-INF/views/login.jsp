<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="POST">
		<input type="hidden" name="action" value="login" />
		<input type="text" name="username" required="required" />
		<input type="text" name="password" required="required" />
		<input type="submit" value="LOG IN">
	</form>
</body>
</html>