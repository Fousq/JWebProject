package kz.zhanbolat.web.presintation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.application.service.UserService;
import kz.zhanbolat.web.domain.entity.User;
import kz.zhanbolat.web.domain.exception.InvalidValueException;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet(urlPatterns="/registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(RegistrationController.class);
    public static final String TELEPHONE_NUMBER_PARAM_NAME = "telephone";
    public static final String COUNTRY_PARAM_NAME = "country";
    public static final String BIRTHDAY_PARAM_NAME = "birthday";
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
		String telephoneNumber = request.getParameter(TELEPHONE_NUMBER_PARAM_NAME);
		String country = request.getParameter(COUNTRY_PARAM_NAME);
		String birthday = request.getParameter(BIRTHDAY_PARAM_NAME);
		boolean created = false;
		User user;
		String errorMessage = null;
		try {
			logger.debug(birthday);
			user = User.newUser().setUsername(username)
					.setPassword(password)
					.setTelephoneNumber(telephoneNumber)
					.setCountry(country)
					.setBirthday(birthday)
					.build();
			created = service.registerNewUser(user);
		} catch (InvalidValueException e) {
			logger.error("Invalid value has been caught on user " + username
					+ ".", e);
			errorMessage = "label.context.registrationInvalidError";
		}
		HttpSession session = request.getSession();
		String page = null;
		if (created) {
			if (session.getAttribute("errorMessage") != null) {
				session.removeAttribute("errorMessage");
			}
			page = "/login";
		} else {
			page = "/registration";
			if (errorMessage == null) {
				errorMessage = "label.context.registrationError";
			}
			session.setAttribute("errorMessage", errorMessage);
		}
		response.sendRedirect(request.getContextPath() + page);
	}
	
}
