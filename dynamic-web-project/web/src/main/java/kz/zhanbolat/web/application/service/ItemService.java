package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.time.LocalDate;

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
	public Item obtainUserItem(long userId) {
		connection = ConnectionPool.INSTANCE.getConnection();
		itemDao.setConnection(connection);
		Item item = null;
		return item;
	}
	
}
