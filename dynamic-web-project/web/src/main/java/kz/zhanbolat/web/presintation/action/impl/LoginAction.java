package kz.zhanbolat.web.presintation.action.impl;

import javax.servlet.http.HttpServletRequest;

import kz.zhanbolat.web.application.service.UserService;
import kz.zhanbolat.web.presintation.action.Action;

public class LoginAction implements Action {
	private static final String LOGIN_PARAM_NAME = "login";
	private static final String PASSWORD_PARAM_NAME = "password";
	private UserService service;
	
	public LoginAction() {
		service = new UserService();
	}
	
	@Override
	public String performe(HttpServletRequest req) {
		String username = req.getParameter(LOGIN_PARAM_NAME);
		String password = req.getParameter(PASSWORD_PARAM_NAME);
		String page;
		if (service.isExisted(username, password)) {
			req.setAttribute("username", username);
			page = "views/main.jsp";
		} else {
			page = "views/error.jsp";
		}
		return page;
	}
	
}
