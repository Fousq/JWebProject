package kz.zhanbolat.web.action;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
	
	public static Action defineAction(HttpServletRequest req) {
		Action action = null;
		ActionType type = ActionType.valueOf(req.getParameter("action").toUpperCase());
		action = type.getAction();
		return action;
	}
	
}
