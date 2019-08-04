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
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class ItemService {
	private static Logger logger = LogManager.getLogger(ItemService.class);
	private AbstractDao<Long, Item> itemDao;
	private Connection connection;
	
	public ItemService() {
		itemDao = new ItemDao();
	}
	
	public List<Item> obtainItems(int limit) {
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		List<Item> items = null;
		try {
			items = ((ItemDao)itemDao).findAll(limit);
		} catch (DaoException e) {
			logger.error("ERROR on obtaining the items.", e);
		}
		return items;
	}
	
	public List<Item> obtainItems(int limit, long offset) {
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		List<Item> items = null;
		try {
			items = ((ItemDao)itemDao).findAll(limit, offset);
		} catch (DaoException e) {
			logger.error("ERROR on obtaining the items.", e);
		}
		return items;
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
	
	public List<Item> obtainUserItems(long userId) {
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		List<Item> items = null;
		try {
			items = ((ItemDao) itemDao).findAllItemByUserId(userId);
		} catch (DaoException e) {
			logger.error("ERROR on obtaining all of the user's items.", e);
		}
		
		return items;
	}
	
	public boolean deleteItem(Item item) {
		boolean isDeleted = false;
		EntityTransaction transaction = new EntityTransaction();
		AbstractDao<Long, Record> recordDao = new RecordDao();
		transaction.begin(itemDao, recordDao);
		try {
			itemDao.delete(item);
			((RecordDao) recordDao).deleteByItemId(item.getId());
			transaction.commit();
		} catch (DaoException e) {
			logger.error("ERROR on deleting the item.", e);
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return isDeleted;
	}
	
	public List<Item> obtainItemsByCategoryId(int categoryId) {
		List<Item> items = null;
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		try {
			items = ((ItemDao) itemDao).findAllByCategoryId(categoryId);
		} catch (DaoException e) {
			logger.error("ERROR on finding all items by category id", e);
		}
		
		return items;
	}
	
	public List<Item> obtainItemsByCategoryId(int categoryId, int limit) {
		List<Item> items = null;
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		try {
			items = ((ItemDao) itemDao).findAllByCategoryId(categoryId, limit);
		} catch (DaoException e) {
			logger.error("ERROR on finding all items by category id", e);
		}
		
		return items;
	}
	
	public List<Item> obtainItemsByCategoryId(int categoryId, int limit, long offset) {
		List<Item> items = null;
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		try {
			items = ((ItemDao) itemDao).findAllByCategoryId(categoryId, limit, offset);
		} catch (DaoException e) {
			logger.error("ERROR on finding all items by category id", e);
		}
		
		return items;
	}
	
}
