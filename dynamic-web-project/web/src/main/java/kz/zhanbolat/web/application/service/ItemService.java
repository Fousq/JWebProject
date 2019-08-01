package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.application.service.transaction.EntityTransaction;
import kz.zhanbolat.web.domain.entity.Item;
import kz.zhanbolat.web.domain.entity.Record;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.ItemDao;
import kz.zhanbolat.web.infrastructer.database.dao.RecordDao;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class ItemService {
	private static Logger logger = LogManager.getLogger(ItemService.class);
	private AbstractDao<Long, Item> itemDao;
	private Connection connection;
	
	public ItemService() {
		itemDao = new ItemDao();
	}
	
	public boolean createNewItem(long userId, Item item) {
		EntityTransaction transaction = new EntityTransaction();
		AbstractDao<Long, Record> recordDao = new RecordDao();
		boolean isCreated;
		transaction.begin(itemDao, recordDao);
		try {
			itemDao.create(item);
			Record record = Record.newBuilder().setActive(true)
					.setCreateAt(LocalDate.now())
					.setUserId(userId)
					.setItemId(((ItemDao) itemDao).getGeneratedId())
					.build();
			isCreated = recordDao.create(record);
			transaction.commit();
		} catch (DaoException e) {
			logger.error("Error on creation new item.", e);
			isCreated = false;
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return isCreated;
	}
	
	/*
	 * Obtain the newest item from the user.
	 */
	public List<Item> obtainUserItems(long userId) {
		EntityTransaction transaction = new EntityTransaction();
		AbstractDao<Long, Record> recordDao = new RecordDao();
		List<Item> items = null;
		transaction.begin(itemDao, recordDao);
		try {
			List<Record> records = 
					((RecordDao) recordDao).findAllByUserId(userId);
			items = new ArrayList<>();
			for (Record record : records) {
				Item item = itemDao.read(record.getItemId());
				items.add(item);
			}
			transaction.commit();
		} catch (DaoException e) {
			transaction.rollback();
		} finally {
			transaction.end();
		}
		
		return items;
	}
	
}
