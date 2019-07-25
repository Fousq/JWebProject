package kz.zhanbolat.web.presintation.action.impl;

import javax.servlet.http.HttpServletRequest;

import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.presintation.action.Action;

public class CreateItemAction implements Action {
	private static final String NAME_PARAM_NAME = "name";
	private static final String DESCRIPTION_PARAM_NAME = "description";
	private static final String PRICE_PARAM_NAME = "price";
	private static final String CATEGORY_ID_PARAM_NAME = "category";
	
	@Override
	public String performe(HttpServletRequest req) {
		ItemService service = new ItemService();
		String name = req.getParameter(NAME_PARAM_NAME);
		String description = req.getParameter(DESCRIPTION_PARAM_NAME);
		int price = Integer.parseInt(req.getParameter(PRICE_PARAM_NAME));
		int categoryId = Integer.parseInt(req.getParameter(CATEGORY_ID_PARAM_NAME));
		service.createNewItem(name, description, price, categoryId);
		String page = "/profile";
		return page;
	}

}
