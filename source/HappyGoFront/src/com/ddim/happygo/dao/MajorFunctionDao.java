package com.ddim.happygo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ddim.happygo.model.Header;
import com.ddim.happygo.model.MajorFunction;
import com.mdbs.jdbc.ClassRowMapperFactory;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2016/01/15 程式摘要：com.ddim.happygo.dao
 * <P>
 * 類別名稱：MajorFunctionDao.java
 * <P>
 * 程式內容說明：最新消息連結資料表
 * <P>
 * 
 * @author Yvonne
 */
@Repository
public class MajorFunctionDao {

	@Autowired
	private SimpleJdbc simpleJdbc;
	public final static String TABLE_NAME = "HG_MAJORFUNCTION";
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void init(DataSource dataSource) throws Exception {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingColumns("ID", "NAME",
				"COLOR", "CURSOR_COLOR", "SHOW_ICON", "STATUS", "TYPE", "LINK", "AD_NAME", "IMAGE", "AD_PROPOSAL",
				"SORT", "CREATOR", "CREATE_TIME", "MODIFIER", "MODIFY_TIME");
	}

	/**
	 * 取得啟用中的分頁
	 * 
	 * @param name
	 * @param status
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public List<MajorFunction> getStateOnPage() throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE STATUS != 0 ");
		sb.append(" ORDER BY SORT ASC");
		return simpleJdbc.query(sb.toString(),ClassRowMapperFactory.get(MajorFunction.class));
	}
}
