/*
 * Created on : 2009/1/15
 */

package com.mdbs.jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Ringo
 */
@Repository
public class PagingDaoImpl implements PagingDao {
	protected JdbcTemplate jdbcTemplate;
	protected RowMapper rowMapper;
	protected PagingQuery pagingQuery;

	public PagingDaoImpl() {
	}

	public PagingDaoImpl(RowMapper rowMapper, JdbcTemplate jdbcTemplate,
			PagingQuery pagingQuery) {
		this.rowMapper = rowMapper;
		this.jdbcTemplate = jdbcTemplate;
		this.pagingQuery = pagingQuery;
	}

	public List query(final String sql) throws Exception {
		return getJdbcTemplate().query(sql, getRowMapper());
	}

	public List query(final String sql, final Object[] args) throws Exception {
		return getJdbcTemplate().query(sql, args, getRowMapper());
	}

	/**
	 * offset is zero base
	 */
	public List query(final String sql, final int offset, final int numbers)
			throws Exception {
		return query(sql, null, offset, numbers);
	}

	/**
	 * offset is zero base
	 */
	public List query(final String sql, final Object[] args, final int offset,
			final int numbers) throws Exception {
		return pagingQuery.query(sql, args, offset, numbers, new JdbcQuery() {
			public List doQuery(final String sql, final Object[] args)
					throws Exception {
				return getJdbcTemplate().query(sql, args, getRowMapper());
			}
		});
	}

	public List queryPage(final String sql, final int page, final int pageItems)
			throws Exception {
		return query(sql, null, pageItems * (page - 1), pageItems);
	}

	public List queryPage(final String sql, final Object[] args,
			final int page, final int pageItems) throws Exception {
		return query(sql, args, pageItems * (page - 1), pageItems);
	}

	public List queryForList(final String sql, final Object[] args)
			throws Exception {
		return getJdbcTemplate().queryForList(sql, args);
	}

	/**
	 * offset is zero base
	 */
	public List queryForList(final String sql, final int offset,
			final int numbers) throws Exception {
		return queryForList(sql, null, offset, numbers);
	}

	/**
	 * offset is zero base
	 */
	public List queryForList(final String sql, final Object[] args,
			final int offset, final int numbers) throws Exception {
		return pagingQuery.query(sql, args, offset, numbers, new JdbcQuery() {
			public List doQuery(final String sql, final Object[] args)
					throws Exception {
				return getJdbcTemplate().queryForList(sql, args);
			}
		});
	}

	public List queryPageForList(final String sql, final int page,
			final int pageItems) throws Exception {
		return queryForList(sql, null, pageItems * (page - 1), pageItems);
	}

	public List queryPageForList(final String sql, final Object[] args,
			final int page, final int pageItems) throws Exception {
		return queryForList(sql, args, pageItems * (page - 1), pageItems);
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @return the rowMapper
	 */
	public RowMapper getRowMapper() {
		return rowMapper;
	}

	/**
	 * @param rowMapper
	 *            the rowMapper to set
	 */
	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}

	/**
	 * @return the pagingQuery
	 */
	public PagingQuery getPagingQuery() {
		return pagingQuery;
	}

	/**
	 * @param pagingQuery
	 *            the pagingQuery to set
	 */
	public void setPagingQuery(PagingQuery pagingQuery) {
		this.pagingQuery = pagingQuery;
	}
}
