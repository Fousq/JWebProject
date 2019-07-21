package kz.zhanbolat.web.action;

public enum ActionType {
	LOGIN(new LoginAction()), LOGOUT(new LogoutAction());
	
	private Action action;
	
	ActionType(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}
	
}
