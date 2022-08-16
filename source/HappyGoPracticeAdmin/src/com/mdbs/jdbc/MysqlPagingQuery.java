/*
 * Created on : 2009/1/5
 */

package com.mdbs.jdbc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Ringo
 */
public class MysqlPagingQuery implements PagingQuery {

	/**
	 * offset is zero base
	 */
	public List query(String sql, Object[] args, int offset, int numbers,
			JdbcQuery jdbcQuery) throws Exception {
		List list = (args != null) ? new LinkedList(Arrays.asList(args))
				: new LinkedList();
		list.add(new Integer(offset));
		list.add(new Integer(numbers));
		return jdbcQuery.doQuery(getPagingSql(sql), list.toArray());
	}

	String getPagingSql(final String sql) {
		return sql + " limit ?,?";
	}
}
