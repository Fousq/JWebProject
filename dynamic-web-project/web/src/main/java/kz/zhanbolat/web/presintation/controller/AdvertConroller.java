package kz.zhanbolat.web.presintation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.zhanbolat.web.application.service.CategoryService;
import kz.zhanbolat.web.domain.entity.Category;
import kz.zhanbolat.web.presintation.action.Action;
import kz.zhanbolat.web.presintation.action.ActionFactory;

/**
 * Servlet implementation class AdvertConroller
 */
@WebServlet(urlPatterns="/advert")
public class AdvertConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertConroller() {
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
		Action action = ActionFactory.defineAction(request);
		String page = action.performe(request);
		response.sendRedirect(request.getContextPath() + page);
	}
	
}
