package kz.zhanbolat.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import kz.zhanbolat.web.application.service.PageService;
import kz.zhanbolat.web.domain.entity.Entity;
import kz.zhanbolat.web.domain.entity.User;

public class PageServiceTest {
	private static Logger logger = LogManager.getLogger(PageServiceTest.class);
	private PageService service;
	
	@Before
	public void init() {
		service = new PageService();
	}
	
	@Test
	public void FourEntityWithPageIdOneShouldBeConvertedCorrectly() {
		List<Entity> entities = new ArrayList<>();
		int size = 4;
		for (int i = 0; i < size; i++) {
			Entity entity = User.newUser().setId((long) i).build();
			entities.add(entity);
		}
		int pageId = 1;
		entities = (List<Entity>) service.convertToPageFormat(pageId, entities);
		entities.forEach(entity -> logger.debug(entity));
		logger.debug("FourEntityWithPageIdOneShouldBeConvertedCorrectly done");
		assertEquals(entities.size(), size);
	}
	
	@Test
	public void FourEntityWithPageIdTwoShouldBeConvertedCorrectly() {
		List<Entity> entities = new ArrayList<>();
		int size = 4;
		for (int i = 0; i < size; i++) {
			Entity entity = User.newUser().setId((long) i).build();
			entities.add(entity);
		}
		int pageId = 2;
		entities = (List<Entity>) service.convertToPageFormat(pageId, entities);
		entities.forEach(entity -> logger.debug(entity));
		logger.debug("FourEntityWithPageIdTwoShouldBeConvertedCorrectly done");
		assertTrue(entities.isEmpty());
	}
	
	@Test
	public void EightEntityWithPageIdOneShouldBeConvertedCorrectly() {
		List<Entity> entities = new ArrayList<>();
		int size = 8;
		int expectedSize = 5;
		for (int i = 0; i < size; i++) {
			Entity entity = User.newUser().setId((long) i).build();
			entities.add(entity);
		}
		int pageId = 1;
		entities = (List<Entity>) service.convertToPageFormat(pageId, entities);
		entities.forEach(entity -> logger.debug(entity));
		logger.debug("EightEntityWithPageIdOneShouldBeConvertedCorrectly done");
		assertEquals(entities.size(), expectedSize);
	}
	
	@Test
	public void EightEntityWithPageIdTwoShouldBeConvertedCorrectly() {
		List<Entity> entities = new ArrayList<>();
		int size = 8;
		int expectedSize = 3;
		for (int i = 0; i < size; i++) {
			Entity entity = User.newUser().setId((long) i).build();
			entities.add(entity);
		}
		int pageId = 2;
		entities = (List<Entity>) service.convertToPageFormat(pageId, entities);
		entities.forEach(entity -> logger.debug(entity));
		logger.debug("EightEntityWithPageIdTwoShouldBeConvertedCorrectly done");
		assertEquals(entities.size(), expectedSize);
	}
	
}
