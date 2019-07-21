<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
	<h1>HELLO</h1>
	<form action="controller" method="post">
		<input type="hidden" name="action" value="login" />
		<input type="text" name="login" required="required" />
		<input type="password" name="password" required="required" />
		<input type="submit" value="LOG IN" />
	</form>
</body>
</html>