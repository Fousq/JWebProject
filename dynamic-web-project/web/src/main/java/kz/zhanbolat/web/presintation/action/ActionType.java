package kz.zhanbolat.web.presintation.action;

import kz.zhanbolat.web.presintation.action.impl.LoginAction;
import kz.zhanbolat.web.presintation.action.impl.LogoutAction;
import kz.zhanbolat.web.presintation.action.impl.RegistrationAction;

public enum ActionType {
	LOGIN(new LoginAction()),
	LOGOUT(new LogoutAction()),
	REGISTR(new RegistrationAction());
	
	private Action action;
	
	private ActionType(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}
	
}
