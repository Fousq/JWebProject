package kz.zhanbolat.web.presintation.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	static final String LOGIN_PARAM_NAME = "login";
	static final String PASSWORD_PARAM_NAME = "password";
	String performe(HttpServletRequest req);
}
