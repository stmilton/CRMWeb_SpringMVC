/*
 * Created on : 2009/1/6
 */

package com.mdbs.jdbc;

import java.util.List;

/**
 * 
 * @author Ringo
 */
public interface PagingQuery {
	/**
	 * offset is zero base
	 */
	<E> List<E> query(String sql, Object[] args, int offset, int numbers,
			JdbcQuery<E> jdbcQuery) throws Exception;
}
