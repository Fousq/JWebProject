package kz.zhanbolat.web.presentation.controller;

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
 * Servlet implementation class EditController
 */
@WebServlet(urlPatterns="/edit")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(EditController.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processGetRequest(request, response);
		request.getRequestDispatcher("views/edit.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processPostRequest(request, response);
	}
	
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		UserService service = new UserService();
		long userId = 
				(long) request.getSession()
				.getAttribute(ProfileController.USER_ID_ATTR_NAME);
		User user = service.obtainUserInfo(userId);
		request.setAttribute("telephone", user.getTelephoneNumber());
		request.setAttribute("country", user.getCountry());
		request.setAttribute("birthday", user.getBirthday().toString());
	}
	
	private void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService service = new UserService();
		HttpSession session = request.getSession();
		long userId = 
				(long) session.getAttribute(ProfileController.USER_ID_ATTR_NAME);
		String telephoneNumber = 
				request.getParameter(RegistrationController.TELEPHONE_NUMBER_PARAM_NAME);
		String country =
				request.getParameter(RegistrationController.COUNTRY_PARAM_NAME);
		String birthday = 
				request.getParameter(RegistrationController.BIRTHDAY_PARAM_NAME);
		boolean isEdited = false;
		try {
			User user = User.newUser().setId(userId)
					.setTelephoneNumber(telephoneNumber)
					.setCountry(country)
					.setBirthday(birthday)
					.build();
			isEdited = service.editUser(user);
		} catch (InvalidValueException e) {
			logger.error("Invalid value has been caught on user " + userId
					+ ".", e);
		}
		String page = null;
		if (isEdited) {
			if (session.getAttribute("errorMessage") != null) {
				session.removeAttribute("errorMessage");
			}
			page = "/profile";
		} else {
			page = "/error";
			session.setAttribute("errorMessage", "ERROR on editing the user.");
		}
		response.sendRedirect(request.getContextPath() + page);
	}
	
}
