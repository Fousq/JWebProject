package kz.zhanbolat.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.zhanbolat.service.UserService;

public class LoginAction implements Action {
	private UserService service;
	
	public LoginAction() {
		service = new UserService();
	}

	@Override
	public void performe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (service.isExisted(username, password)) {
			req.getSession().setAttribute("username", username);
			resp.sendRedirect("/main");
		}
	}
	
}
