package kz.zhanbolat.web.presintation.action;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
	
	public static Action defineAction(HttpServletRequest req) {
		ActionType type = ActionType.valueOf(req.getParameter("action").toUpperCase());
		return type.getAction();
	}
	
}
