package kz.zhanbolat.web.presintation.action.impl;

import javax.servlet.http.HttpServletRequest;

import kz.zhanbolat.web.presintation.action.Action;

public class LogoutAction implements Action {

	@Override
	public String performe(HttpServletRequest req) {
		String page = "/";
		req.getSession().invalidate();
		return page;
	}

}
