package com.ddim.happygo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.mdbs.jdbc.MysqlPagingQuery;
import com.mdbs.jdbc.PagingQuery;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2015/03/23
 * <P>
 * 類別名稱：BaseConfig.java
 * <P>
 * 程式內容說明：覆寫SimpleJdbc使用資料庫型態
 * <P>
 * 
 * @author Henry
 * */
@Configuration
public class BaseConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	@Autowired
	public TransactionTemplate transactionTemplate(
			DataSourceTransactionManager transactionManager) {
		return new TransactionTemplate(transactionManager);
	}

	@Bean
	public PagingQuery pagingQuery() {
		return new MysqlPagingQuery();
	}

	@Bean
	@Autowired
	public SimpleJdbc simpleJdbc(JdbcTemplate jdbcTemplate,
			PagingQuery pagingQuery) {
		return new SimpleJdbc(jdbcTemplate, pagingQuery);
	}
}
