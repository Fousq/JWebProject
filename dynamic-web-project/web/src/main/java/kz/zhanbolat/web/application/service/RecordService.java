package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Record;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.RecordDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class RecordService {
	private static Logger logger = LogManager.getLogger(RecordService.class);
	private AbstractDao<Long, Record> dao;
	private Connection connection;
	
	public RecordService() {
		dao = new RecordDao();
	}
	
	public List<Record> obtainRecords(int limit, long offset) {
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		List<Record> records = null;
		try {
			records = ((RecordDao) dao).findAll(limit, offset);
		} catch (DaoException e) {
			logger.error("ERROR on finding all records.", e);
		}
		return records;
	}
	
	public List<Record> obtainRecordsListByUserId(long userId) {
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		List<Record> records = null;
		try {
			records = ((RecordDao) dao).findAllByUserId(userId);
		} catch (DaoException e) {
			logger.error("ERROR on finding all records of user.", e);
		}
		return records;
	}
	
	public List<Record> obtainRecordsByCategoryId(int categoryId) {
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		List<Record> records = null;
		try {
			records = ((RecordDao) dao).findAllRecordsByCategoryId(categoryId);
		} catch (DaoException e) {
			logger.error("ERROR on finding all records");
		}
		
		return records;
	}
	
	public List<Record> obtainRecordsByCategoryId(int categoryId, int limit) {
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		List<Record> records = null;
		try {
			records = ((RecordDao) dao).findAllRecordsByCategoryId(categoryId, limit);
		} catch (DaoException e) {
			logger.error("ERROR on finding all records");
		}
		
		return records;
	}
	
	public List<Record> obtainRecordsByCategoryId(int categoryId, int limit, long offset) {
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		List<Record> records = null;
		try {
			records = ((RecordDao) dao).findAllRecordsByCategoryId(categoryId, limit, offset);
		} catch (DaoException e) {
			logger.error("ERROR on finding all records");
		}
		
		return records;
	}
	
	
	
}
