package kz.zhanbolat.web.presintation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.zhanbolat.web.application.service.UserService;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet(urlPatterns="/registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("views/registration.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processPostRequest(request, response);
	}
	
	private void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService service = new UserService();
		String username = request.getParameter(LoginController.LOGIN_PARAM_NAME);
		String password = request.getParameter(LoginController.PASSWORD_PARAM_NAME);
		boolean created = service.registerNewUser(username, password);
		String page = null;
		if (created) {
			request.getSession().setAttribute("errorMessage", null);
			page = "/login";
		} else {
			page = "/registration";
			request.getSession().setAttribute("errorMessage", "label.context.registrationError");
		}
		response.sendRedirect(request.getContextPath() + page);
	}
	
}
