package kz.zhanbolat.database.dao;

import java.sql.Connection;
import java.util.List;

import kz.zhanbolat.entity.Entity;

public abstract class AbstractDao<K, T extends Entity> {
	
	protected Connection connection;
	
	public abstract List<T> findAll();
	public abstract boolean create(T entity);
	public abstract T read(K id);
	public abstract boolean update(T entity);
	public abstract boolean delete(T entity);
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
