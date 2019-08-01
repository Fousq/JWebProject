package kz.zhanbolat.web.presintation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.application.service.CategoryService;
import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.application.service.RecordService;
import kz.zhanbolat.web.domain.entity.Category;
import kz.zhanbolat.web.domain.entity.Item;

/**
 * Servlet implementation class AdvertConroller
 */
@WebServlet(urlPatterns="/advert")
public class AdvertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(AdvertController.class);
	private static final String NAME_PARAM_NAME = "name";
	private static final String DESCRIPTION_PARAM_NAME = "description";
	private static final String PRICE_PARAM_NAME = "price";
	private static final String CATEGORY_ID_PARAM_NAME = "category";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processGetRequest(request, response);
		request.getRequestDispatcher("views/advert.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processPostRequest(request, response);
	}
	
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		CategoryService service = new CategoryService();
		List<Category> categories = service.obtainAllCategories();
		request.setAttribute("categories", categories);
	}
	
	private void processPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		boolean isCreated = createItem(request);
		if (isCreated) {
			response.sendRedirect(request.getContextPath() + "/profile");
		} else {
			if (session.getAttribute("errorMessage") == null) {
				session.setAttribute("errorMessage", "label.error.create");
			}
			response.sendRedirect(request.getContextPath() + "/advert");
		}
	}
	
	private boolean createItem(HttpServletRequest request) {
		ItemService service = new ItemService();
		long userId = (long) request.getSession().getAttribute("id");
		String name = request.getParameter(NAME_PARAM_NAME);
		String description = request.getParameter(DESCRIPTION_PARAM_NAME);
		int price = 0;
		int categoryId = 0;
		boolean isCreated = false;
		try {
			price = Integer.parseInt(request.getParameter(PRICE_PARAM_NAME));
			categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID_PARAM_NAME));
			Item item = Item.newBuilder().setName(name)
					.setDescription(description)
					.setPrice(price)
					.setCategoryId(categoryId)
					.build();
			isCreated = service.createNewItem(userId, item);
		} catch (IllegalArgumentException e) {
			logger.error("Error in parsing the parameter to integer.", e);
			request.getSession().setAttribute("errorMessage", "label.error.argument");
		}
		return isCreated;
	}
	
}
