package kz.zhanbolat.web.infrastructer.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.web.domain.entity.Record;
import kz.zhanbolat.web.infrastructer.exception.DaoException;

public class RecordDao implements AbstractDao<Long, Record> {
	private static Logger logger = LogManager.getLogger(RecordDao.class);
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;
	
	public RecordDao() {
		
	}
	
	public RecordDao(Connection connection) {
		this.connection = connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<Record> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Record entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Record read(Long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Record entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Record entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
