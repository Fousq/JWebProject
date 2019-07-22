package kz.zhanbolat.web.presintation.action.impl;

import javax.servlet.http.HttpServletRequest;

import kz.zhanbolat.web.application.service.UserService;
import kz.zhanbolat.web.presintation.action.Action;

public class RegistrationAction implements Action {
	private UserService service;
	
	public RegistrationAction() {
		service = new UserService();
	}

	@Override
	public String performe(HttpServletRequest req) {
		String username = req.getParameter(LOGIN_PARAM_NAME);
		String password = req.getParameter(PASSWORD_PARAM_NAME);
		boolean created = service.registerNewUser(username, password);
		String page = null;
		if (created) {
			page = "/login";
		} else {
			page = "/error";
			req.getSession().setAttribute("errorMessage", "Such user exists.");
		}
		return page;
	}

}
