package kz.zhanbolat.web.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.domain.entity.Item;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet(urlPatterns="/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processGetRequest(request, response);
		response.sendRedirect(request.getContextPath() + "/manage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		long itemId = Long.parseLong(request.getParameter("item"));
		ItemService service = new ItemService();
		Item item = Item.newBuilder().setId(itemId)
				.build();
		service.deleteItem(item);
	}
	
}
