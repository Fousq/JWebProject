package kz.zhanbolat.web.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.zhanbolat.web.application.service.UserService;
import kz.zhanbolat.web.domain.entity.User;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet(urlPatterns="/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String USER_ID_ATTR_NAME = "id";
	private static final String TELEPHONE_NUMBER_DEFAUL_VALUE = "No number";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processGetRequest(request, response);
		request.getRequestDispatcher("views/profile.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		UserService service = new UserService();
		long userId = (long) request.getSession().getAttribute(USER_ID_ATTR_NAME);
		User user = service.obtainUserInfo(userId);
		request.setAttribute("username", user.getUsername());
		if (user.getTelephoneNumber().isEmpty() || user.getTelephoneNumber() == null) {
			request.setAttribute("telephone", TELEPHONE_NUMBER_DEFAUL_VALUE);
		} else {
			request.setAttribute("telephone", user.getTelephoneNumber());
		}
		request.setAttribute("country", user.getCountry());
		request.setAttribute("birthday", user.getBirthday());
	}
	
}
