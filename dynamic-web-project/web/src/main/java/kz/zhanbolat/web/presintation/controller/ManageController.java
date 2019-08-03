package kz.zhanbolat.web.presintation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.application.service.PageService;
import kz.zhanbolat.web.application.service.RecordService;
import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.domain.entity.Record;

/**
 * Servlet implementation class ManageController
 */
@WebServlet(urlPatterns="/manage")
public class ManageController extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ManageController.class);
	private static final long serialVersionUID = 1L;
    private boolean next;
    private boolean previous;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageController() {
        super();
        // TODO Auto-generated constructor stub
        next = true;
        previous = false;
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
	
	@SuppressWarnings("unchecked")
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		long userId = (long) request.getSession().getAttribute("id");
		int pageId = Integer.parseInt(request.getParameter("page"));
		RecordService recordService = new RecordService();
		ItemService itemService = new ItemService();
		List<Record> records = recordService.obtainRecordsListByUserId(userId);
		List<Item> items = itemService.obtainUserItems(userId);
		PageService pageService = new PageService();
		records = (List<Record>) pageService.convertToPageFormat(pageId, records);
		items = (List<Item>) pageService.convertToPageFormat(pageId, items);
		logger.debug(records.size());
		logger.debug(items.size());
		if (records.size() < PageService.DEFAULT_PAGE_SIZE) {
			next = false;
		} else {
			next = true;
		}
		if (pageId > 1) {
			previous = true;
		} else {
			previous = false;
		}
		request.setAttribute("records", records);
		request.setAttribute("items", items);
		request.setAttribute("next", next);
		request.setAttribute("previous", previous);
	}
	
}
