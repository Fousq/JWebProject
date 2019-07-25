<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="edit" method="post">
		<input type="hidden" name="action" value="edit" />
		<label for="telephone">Telephone number:</label>
		<input type="text" name="telephone" id="telephone" /><br>
		<label for="country">Country:</label>
		<input type="text" name="country" id="country" /><br>
		<label for="birthday">Birthday:</label>
		<input type="date" name="birthday" id="birthday" /><br>
		<input type="submit" value="EDIT" />
	</form>
</body>
</html>