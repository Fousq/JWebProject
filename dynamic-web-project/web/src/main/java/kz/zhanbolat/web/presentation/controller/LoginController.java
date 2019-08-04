package kz.zhanbolat.web.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kz.zhanbolat.web.application.service.UserService;
import kz.zhanbolat.web.domain.entity.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns="/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String LOGIN_PARAM_NAME = "login";
    public static final String PASSWORD_PARAM_NAME = "password";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("views/login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processPostRequest(request, response);
	}

	private void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		String username = request.getParameter(LOGIN_PARAM_NAME);
		String password = request.getParameter(PASSWORD_PARAM_NAME);
		String page;
		HttpSession session = request.getSession();
		if (service.isExisted(username, password)) {
			User user = service.obtainUserInfo(username);
			session.setAttribute("id", user.getId());
			if (session.getAttribute("errorMessage") != null) {
				session.removeAttribute("errorMessage");
			}
			page = "/";
		} else {
			page = "/login";
			request.getSession().setAttribute("errorMessage", "label.context.loginError");
		}
		response.sendRedirect(request.getContextPath() + page);
	}
	
}
