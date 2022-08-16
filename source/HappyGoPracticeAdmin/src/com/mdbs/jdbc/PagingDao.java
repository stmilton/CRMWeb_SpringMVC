/*
 * Created on : 2009/1/5
 */
package com.mdbs.jdbc;

import java.util.List;

/**
 * 
 * @author Ringo
 */
public interface PagingDao {

	List queryPage(String sql, int page, int pageItems) throws Exception;

	List queryPage(String sql, Object[] args, int page, int pageItems)
			throws Exception;

	List queryPageForList(String sql, int page, int pageItems) throws Exception;

	List queryPageForList(String sql, Object[] args, int page, int pageItems)
			throws Exception;

	List query(String sql) throws Exception;

	List query(String sql, Object[] args) throws Exception;

	/**
	 * zero base
	 */
	List query(String sql, Object[] args, int offset, int numbers)
			throws Exception;

	/**
	 * zero base
	 */
	List query(String sql, int offset, int numbers) throws Exception;

	/**
	 * zero base
	 */
	List queryForList(String sql, Object[] args, int offset, int numbers)
			throws Exception;

	/**
	 * zero base
	 */
	List queryForList(String sql, int offset, int numbers) throws Exception;

	List queryForList(String sql, Object[] args) throws Exception;
}
