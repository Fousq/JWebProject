package kz.zhanbolat.web;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import kz.zhanbolat.web.application.service.ItemService;
import kz.zhanbolat.web.domain.entity.Item;

public class ItemServiceTest {
	private static Logger logger = LogManager.getLogger(ItemServiceTest.class);
	private ItemService service;
	
	@Before
	public void init() {
		service = new ItemService();
	}
	
	@Test
	@Ignore
	public void newItemShouldBeCreated() {
		String name = "name";
		String description = "description";
		int price = 123;
		int categoryId = 2;
		Item item = Item.newBuilder().setName(name)
				.setDescription(description)
				.setPrice(price)
				.setCategoryId(categoryId)
				.build();
		int userId = 11;
		boolean created = service.createNewItem(userId, item);
		assertTrue(created);
	}
	
}
