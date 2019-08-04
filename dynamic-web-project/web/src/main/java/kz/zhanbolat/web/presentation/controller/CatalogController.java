package kz.zhanbolat.web.presentation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.application.service.CategoryService;
import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.application.service.PageService;
import kz.zhanbolat.web.application.service.RecordService;
import kz.zhanbolat.web.domain.entity.Category;
import kz.zhanbolat.web.domain.entity.Entity;
import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.domain.entity.Record;

/**
 * Servlet implementation class CatalogController
 */
@WebServlet(urlPatterns="/catalog")
public class CatalogController extends HttpServlet {
	private static Logger logger = LogManager.getLogger(CatalogController.class);
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_LIMIT = 10;
    private long itemOffset;
    private long recordOffset;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogController() {
        super();
        // TODO Auto-generated constructor stub
        itemOffset = 0;
        recordOffset = 0;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processGetRequest(request, response);
		request.getRequestDispatcher("views/catalog.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId;
		try {
			categoryId = Integer.parseInt(request.getParameter("category"));
		} catch (IllegalArgumentException e) {
			categoryId = 0;
		}
		itemOffset = 0;
		recordOffset = 0;
		response.sendRedirect(request.getContextPath() + "/catalog?page=" + 1 + "&selected=" + categoryId);
	}
	
	@SuppressWarnings("unchecked")
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) {
		categoryList(request);
		int categoryId;
		try {
			categoryId = Integer.parseInt(request.getParameter("selected"));
		} catch (IllegalArgumentException e) {
			categoryId = 0;
		}
		logger.debug("categoryId: " + categoryId);
		List<Item> items = null;
		List<Record> records = null;
		boolean next = false;
		boolean previous = false;
		PageService pageService = new PageService();
		int pageId = Integer.parseInt(request.getParameter("page"));
		if (categoryId != 0) {
			int prevPageId = (int) request.getSession().getAttribute("prevPage");
			logger.debug("prevPageId: " + prevPageId);
			logger.debug("pageId: " + pageId);
			if (pageId < prevPageId) {
				itemOffset = (long) request.getSession().getAttribute("prevItemOffset");
				recordOffset = (long) request.getSession().getAttribute("prevRecordOffset");
			}
			logger.debug("itemOffset: " + itemOffset);
			logger.debug("recordOffset: " + recordOffset);
			items = itemList(request, categoryId, pageId);
			records = recordList(request, categoryId, pageId);
			items = (List<Item>) pageService.convertToPageFormat(2 - (pageId % 2), items);
			records = (List<Record>) pageService.convertToPageFormat(2 - (pageId % 2), records);
			next = checkNext(items) && checkNext(records);
			previous = checkPrevious(pageId);
		}
		request.setAttribute("items", items);
		request.setAttribute("records", records);
		request.setAttribute("next", next);
		request.setAttribute("previous", previous);
		request.getSession().setAttribute("prevPage", pageId);
	}
	
	private void categoryList(HttpServletRequest request) {
		CategoryService service = new CategoryService();
		List<Category> categories = service.obtainAllCategories();
		request.setAttribute("categories", categories);
	}
	
	@SuppressWarnings("unchecked")
	private List<Item> itemList(HttpServletRequest request, int categoryId, int pageId) {
		ItemService itemService = new ItemService();
		List<Item> items = null;
		if (pageId % 2 == 1) {
			items = itemService.obtainItemsByCategoryId(categoryId, DEFAULT_LIMIT, itemOffset);
			logger.debug(items);
			request.getSession().setAttribute("itemsStore", items);
			if (!(items == null || items.isEmpty())) {
				request.getSession().setAttribute("prevItemOffset", itemOffset);
				itemOffset = items.get(items.size() - 1).getId();
			}
		} else {
			items = (List<Item>) request.getSession().getAttribute("itemsStore");
		}
		logger.debug(items.size());
		return items;
	}
	
	@SuppressWarnings("unchecked")
	private List<Record> recordList(HttpServletRequest request, int categoryId, int pageId) {
		RecordService recordService = new RecordService();
		List<Record> records = null;
		if (pageId % 2 == 1) {
			records = recordService.obtainRecordsByCategoryId(categoryId, DEFAULT_LIMIT, recordOffset);
			request.getSession().setAttribute("recordsStore", records);
			logger.debug(records);
			if (!(records == null || records.isEmpty())) {
				request.getSession().setAttribute("prevRecordOffset", recordOffset);
				recordOffset = records.get(records.size() - 1).getId();
			}
		} else {
			records = (List<Record>) request.getSession().getAttribute("recordsStore");
		}
		logger.debug(records.size());
		return records;
	}
	
	private boolean checkNext(List<? extends Entity> entities) {
		boolean next;
		logger.debug(entities.size());
		if (entities.size() < PageService.DEFAULT_PAGE_SIZE) {
			next = false;
		} else {
			next = true;
		}
		return next;
	}
	
	private boolean checkPrevious(int pageId) {
		boolean previous;
		if (pageId > 1) {
			previous = true;
		} else {
			previous = false;
		}
		return previous;
	}
	
}
