package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.web.application.service.ItemService;

public class ItemServiceTest {
	private static Logger logger = LogManager.getLogger(ItemServiceTest.class);
	private ItemService service;
	
	@Before
	public void init() {
		service = new ItemService();
	}
	
	@Test
	public void newItemShouldBeCreated() {
		String name = "name";
		String description = "description";
		int price = 123;
		int categoryId = 2;
		boolean created = service.createNewItem(name, description, price, categoryId);
		assertTrue(created);
	}
	
}
