package com.mdbs.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Henry
 *
 */
public class MssqlPagingQuery implements PagingQuery {
	/**
	 * offset is zero base
	 */
	public List query(String sql, Object[] args, int offset, int numbers, JdbcQuery jdbcQuery) throws Exception {
		List list = (args != null) ? new ArrayList(Arrays.asList(args)) : new ArrayList();
		int pageStart = offset + 1;
		int pageEnd = numbers + offset;
		list.add(new Integer(pageEnd));
		list.add(new Integer(pageStart));
		return jdbcQuery.doQuery(getPagingSql(sql), list.toArray());
	}

	/**
	 * 
	 * @param oldSql
	 * @return
	 */
	String getPagingSql(final String oldSql) {
		String sql = oldSql.toLowerCase();
		// to get order by
		int orderByPos = sql.lastIndexOf(" order by ");
		String orderBy = sql.substring(orderByPos);
		// must skip "."
		try {
			int dotPos = orderBy.lastIndexOf(".");
			if (0 <= dotPos) {
				orderBy = " order by " + orderBy.substring(dotPos + 1);
			}
		} catch (Exception e) {
		}

		String sqlWithoutOrderBy = sql.substring(0, orderByPos);
		sql = "select * from (select row_number() over (order by rownum) as row_num, * from ( select *, row_number() over ("
				+ orderBy + ") as rownum from (" + sqlWithoutOrderBy
				+ ") as dt0 ) as dt1 where dt1.rownum <= ?) as dt2 where dt2.row_num >= ? ";

		return sql;
	}
}