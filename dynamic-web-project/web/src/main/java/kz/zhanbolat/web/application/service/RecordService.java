package kz.zhanbolat.web.application.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Record;
import kz.zhanbolat.web.infrastructer.database.dao.AbstractDao;
import kz.zhanbolat.web.infrastructer.database.dao.RecordDao;
import kz.zhanbolat.web.infrastructer.database.pool.ConnectionPool;

public class RecordService {
	private static Logger logger = LogManager.getLogger(RecordService.class);
	private AbstractDao dao;
	private Connection connection;
	
	public RecordService() {
		dao = new RecordDao();
	}
	
	public List<Record> obtainRecordsListByUserId(long userId) {
		connection = ConnectionPool.INSTANCE.getConnection();
		dao.setConnection(connection);
		List<Record> records = new ArrayList<>();
		return null;
	}
	
	public boolean createNewRecord(Record record, String username) {
		boolean isCreated = false;
		return isCreated;
	}
	
}
