/*
 * Created on : 2009/1/6
 */

package com.mdbs.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ringo
 */
public class OraclePagingQuery implements PagingQuery {
	/**
     * offset is zero base
     */
    public List query(String sql, Object[] args, int offset, int numbers, JdbcQuery jdbcQuery) throws Exception {
		List list = (args != null) ? new ArrayList(Arrays.asList(args)) : new ArrayList();
        list.add(new Integer(offset + numbers));
        list.add(new Integer(offset));
		return jdbcQuery.doQuery(getPagingSql(sql), list.toArray());
	}


    String getPagingSql(final String sql) {
        return "select * from ( select row_.*, rownum rownum_ from (" + 
				sql +
				") row_ where rownum <= ?) where rownum_ > ?";
    }        
}
