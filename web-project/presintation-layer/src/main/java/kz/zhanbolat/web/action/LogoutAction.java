package kz.zhanbolat.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {

	@Override
	public void performe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/");
		req.getSession().invalidate();
	}

}
