<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<input type="hidden" name="action" value="login" />
		<input type="text" name="login" required />
		<input type="password" name="password" id="password" required />
		<input type="checkbox" id="togglePassword" />
		<input type="submit" value="LOG IN" />
	</form>
	<a href="registration">Registration</a>
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