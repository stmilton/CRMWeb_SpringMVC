/*
 * Created on : 2011/2/24
 */
package com.mdbs.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Ringo
 */
public class SimpleJdbc {
	private JdbcTemplate jdbcTemplate;
	private PagingQuery pagingQuery;

	public SimpleJdbc() {
	}

	public SimpleJdbc(JdbcTemplate jdbcTemplate, PagingQuery pagingQuery) {
		this.jdbcTemplate = jdbcTemplate;
		this.pagingQuery = pagingQuery;
	}

	public <T> Page<T> findPage(final String sql, final Object[] values,
			final PagingParameter paging, final RowMapper<T> rowMapper)
			throws Exception {
		final Page<T> page = new Page<T>();
		int pageSize = paging.getPageSize();
		int pageNo = paging.getPageNo();
		int pageCount = paging.getPagesAvailable();
		int itemCount = paging.getItemsAvailable();
		page.setItemsAvailable(itemCount);
		page.setPageSize(pageSize);
		page.setPageNo(pageNo <= 0 ? 1 : pageNo);
		page.setPagesAvailable(pageCount <= 0 ? 1 : pageCount);
		page.setPageItems(this.queryPage(sql, values, page.getPageNo(),
				pageSize, rowMapper));
		if (page.getPageNo() >= page.getPagesAvailable()
				|| page.getPageItems().size() < pageSize
				|| (page.getPageNo() - 1) * pageSize >= itemCount) {
			final int rowCount = countRow(sql, values);
			page.setItemsAvailable(rowCount);
			if (rowCount >= 1) {
				page.setPagesAvailable(rowCount / pageSize);
				if (rowCount > pageSize * page.getPagesAvailable()) {
					page.setPagesAvailable(page.getPagesAvailable() + 1);
				}
				if (page.getPageNo() > page.getPagesAvailable()) {
					page.setPageNo(page.getPagesAvailable());
					page.setPageItems(this.queryPage(sql, values,
							page.getPageNo(), pageSize, rowMapper));
				}
			} else {
				page.setPageNo(1);
				page.setPagesAvailable(1);
			}
		}
		return page;
	}

	public Page<Map<String, Object>> findPage(final String sql,
			final Object[] values, final PagingParameter paging)
			throws Exception {
		final Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		int pageSize = paging.getPageSize();
		int pageNo = paging.getPageNo();
		int pageCount = paging.getPagesAvailable();
		page.setItemsAvailable(paging.getItemsAvailable());
		page.setPageSize(pageSize);
		page.setPageNo(pageNo <= 0 ? 1 : pageNo);
		page.setPagesAvailable(pageCount <= 0 ? 1 : pageCount);
		page.setPageItems(this.queryPageForList(sql, values, page.getPageNo(),
				pageSize));
		if (page.getPageNo() >= page.getPagesAvailable()
				|| page.getPageItems().size() < pageSize) {
			final int rowCount = countRow(sql, values);
			page.setItemsAvailable(rowCount);
			if (rowCount >= 1) {
				page.setPagesAvailable(rowCount / pageSize);
				if (rowCount > pageSize * page.getPagesAvailable()) {
					page.setPagesAvailable(page.getPagesAvailable() + 1);
				}
				if (page.getPageNo() > page.getPagesAvailable()) {
					page.setPageNo(page.getPagesAvailable());
					page.setPageItems(this.queryPageForList(sql, values,
							page.getPageNo(), pageSize));
				}
			} else {
				page.setPageNo(1);
				page.setPagesAvailable(1);
			}
		}
		return page;
	}

	int countRow(final String sql, final Object[] values) throws Exception {
		return queryForInt("select count(1) from (" + sqlWithoutOrderBy(sql)
				+ ") tmp", values);
	}

	String sqlWithoutOrderBy(final String sql) throws Exception {
		String sqlOptimized = sql.toLowerCase();
		int endIndex = sqlOptimized.lastIndexOf(" order by ");
		try {
			if (sqlOptimized.substring(endIndex).indexOf(")") < 0) {
				// with order by and is not subquery -> cut order by
				return sql.substring(0, endIndex);
			}
		} catch (Exception e) {
		}
		return sql;
	}

	public <T> List<T> queryPage(final String sql, final int pageNo,
			final int pageSize, final RowMapper<T> rowMapper) throws Exception {
		return query(sql, null, pageSize * (pageNo - 1), pageSize, rowMapper);
	}

	public <T> List<T> queryPage(final String sql, final Object[] values,
			final int pageNo, final int pageSize, final RowMapper<T> rowMapper)
			throws Exception {
		return query(sql, values, pageSize * (pageNo - 1), pageSize, rowMapper);
	}

	public List<Map<String, Object>> queryPageForList(final String sql,
			final int pageNo, final int pageSize) throws Exception {
		return queryForList(sql, null, pageSize * (pageNo - 1), pageSize);
	}

	public List<Map<String, Object>> queryPageForList(final String sql,
			final Object[] values, final int pageNo, final int pageSize)
			throws Exception {
		return queryForList(sql, values, pageSize * (pageNo - 1), pageSize);
	}

	public <T> List<T> query(final String sql, final RowMapper<T> rowMapper)
			throws Exception {
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	public <T> List<T> query(final String sql, final Object[] values,
			final RowMapper<T> rowMapper) throws Exception {
		return this.jdbcTemplate.query(sql, values, rowMapper);
	}

	public <T> List<T> query(final String sql, final Object[] values,
			final Class<T> clazz) throws Exception {
		return this.jdbcTemplate.queryForList(sql, values, clazz);
	}

	/**
	 * offset is zero base
	 */
	public <T> List<T> query(final String sql, final int offset,
			final int numbers, final RowMapper<T> rowMapper) throws Exception {
		return query(sql, null, offset, numbers, rowMapper);
	}

	/**
	 * offset is zero base
	 */
	public <T> List<T> query(final String sql, final Object[] values,
			final int offset, final int numbers, final RowMapper<T> rowMapper)
			throws Exception {
		return pagingQuery.query(sql, values, offset, numbers,
				new JdbcQuery<T>() {

					@Override
					public List<T> doQuery(final String sql, final Object[] args)
							throws Exception {
						return jdbcTemplate.query(sql, args, rowMapper);
					}
				});
	}

	/**
	 * offset is zero base
	 */
	public List<Map<String, Object>> queryForList(final String sql,
			final int offset, final int numbers) throws Exception {
		return queryForList(sql, null, offset, numbers);
	}

	/**
	 * offset is zero base
	 */
	public List<Map<String, Object>> queryForList(final String sql,
			final Object[] values, final int offset, final int numbers)
			throws Exception {
		return pagingQuery.query(sql, values, offset, numbers,
				new JdbcQuery<Map<String, Object>>() {

					@Override
					public List<Map<String, Object>> doQuery(final String sql,
							final Object[] args) throws Exception {
						return jdbcTemplate.queryForList(sql, args);
					}
				});
	}

	public <T> List<T> queryForList(final String sql, Class<T> clazz,
			final Object[] values) throws Exception {
		return this.jdbcTemplate.queryForList(sql, clazz, values);
	}

	public List<Map<String, Object>> queryForList(final String sql,
			final Object... values) throws Exception {
		return this.jdbcTemplate.queryForList(sql, values);
	}

	public int queryForInt(final String sql, final Object... values)
			throws Exception {
		try {
			Integer result = this.jdbcTemplate.queryForObject(sql, values, Integer.class);
			return (result != null) ? result : 0;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public long queryForLong(final String sql, final Object... values)
			throws Exception {
		try {
			Long result = this.jdbcTemplate.queryForObject(sql, values, Long.class);
			return (result != null) ? result : 0;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public String queryForString(final String sql, final Object... values)
			throws Exception {
		try {
			return this.jdbcTemplate.queryForObject(sql, String.class, values);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int update(final String sql, final Object... values)
			throws Exception {
		return this.jdbcTemplate.update(sql, values);
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @param pagingQuery
	 *            the pagingQuery to set
	 */
	public void setPagingQuery(PagingQuery pagingQuery) {
		this.pagingQuery = pagingQuery;
	}

	public <T> T getFirst(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public int update(PreparedStatementCreator preparedStatementCreator,
			KeyHolder keyHolder) throws Exception {
		return jdbcTemplate.update(preparedStatementCreator, keyHolder);
	}

	public <T> int insert(SimpleJdbcInsert simpleJdbcInsert, T domain)
			throws Exception {
		return simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(
				domain));
	}

	public <T> T queryForObject(final String sql, final Object[] values,
			final RowMapper rowMapper) throws Exception {
		List<T> list = this.jdbcTemplate.query(sql, values, rowMapper);
		return (!list.isEmpty()) ? list.get(0) : null;
	}

	/**
	 * The query is expected to be a single row/single column query
	 * @param <T>
	 * @param stmt
	 * @param requiredType
	 * @param args
	 * @return
	 * @throws java.lang.Exception
	 */
	public <T> T queryForObject(String stmt,
                            Class<T> requiredType,
                            Object... args) throws Exception {
		return this.jdbcTemplate.queryForObject(stmt, requiredType, args);
	}

	public Map<String, Object> queryForMap(final String stmt, final Object... values) throws Exception {
		List<Map<String, Object>> list = queryForList(stmt, values);
		return (!list.isEmpty()) ? list.get(0) : null;
	}
}
