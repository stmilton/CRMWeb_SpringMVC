/*
 * Created on : 2009/7/17
 */

package com.mdbs.jdbc;

import java.util.List;

/**
 * 
 * @author Ringo
 */
public interface JdbcQuery<E> {
	List<E> doQuery(String sql, Object[] args) throws Exception;
}
