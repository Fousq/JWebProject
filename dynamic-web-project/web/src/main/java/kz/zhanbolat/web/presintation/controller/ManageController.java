package kz.zhanbolat.web.presintation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.application.service.RecordService;
import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.domain.entity.Record;

/**
 * Servlet implementation class ManageController
 */
@WebServlet(urlPatterns="/manage")
public class ManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processGetRequest(request, response);
		request.getRequestDispatcher("views/manage.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		long userId = (long) request.getSession().getAttribute("id");
		RecordService recordService = new RecordService();
		ItemService itemService = new ItemService();
		List<Record> records = recordService.obtainRecordsListByUserId(userId);
		List<Item> items = itemService.obtainUserItems(userId);
		request.setAttribute("records", records);
		request.setAttribute("items", items);
	}
	
}
