/**
 * 
 */
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