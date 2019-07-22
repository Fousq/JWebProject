<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>Insert title here</title>
</head>
<body>
	<form action="registration" method="post">
		<input type="hidden" name="action" value="registr"/>
		<input type="text" name="login" required />
		<input type="password" name="password" id="password" required />
		<input type="checkbox" id="togglePassword" />
		<input type="submit" value="REGISTRATE" />
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