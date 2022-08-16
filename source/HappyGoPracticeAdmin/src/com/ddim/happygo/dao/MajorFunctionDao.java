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
	 * 取得分頁
	 * 
	 * @param name
	 * @param status
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public Page<MajorFunction> findPage(String name, String status, PagingParameter paging) throws Exception {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID IS NOT NULL");
		if (StringUtils.isNotBlank(name)) {
			sb.append(" AND NAME LIKE ?");
			args.add("%" + name + "%");
		}
		if (StringUtils.isNotBlank(status)) {
			sb.append(" AND STATUS = ?");
			args.add(status);
		}
		sb.append(" ORDER BY SORT ASC");
		return simpleJdbc.findPage(sb.toString(), args.toArray(), paging,
				ClassRowMapperFactory.get(MajorFunction.class));
	}

	/**
	 * 取得總筆數
	 * 
	 * @return
	 * @throws Exception
	 */
	public int countList() throws Exception {
		StringBuffer sb = new StringBuffer("SELECT COUNT(1) FROM ");
		sb.append(TABLE_NAME);
		List<Object> args = new ArrayList<Object>();
		return simpleJdbc.queryForInt(sb.toString(), args.toArray());
	}
	
	/**
	 * 取得所有主選單名稱
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> namesList() throws Exception {
		StringBuffer sb = new StringBuffer("SELECT NAME FROM ");
		sb.append(TABLE_NAME);
		List<Object> args = new ArrayList<Object>();
		List<Map<String, Object>> allData = simpleJdbc.queryForList(sb.toString(), args.toArray());
		List<String> names = new ArrayList<String>();
		for (Map<String, Object> map : allData) {
			names.add(map.get("NAME").toString());
		}
		return names;
	}

	/**
	 * 依ID取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MajorFunction get(String id) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID=?");
		Object[] args = { id };
		return simpleJdbc
				.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(MajorFunction.class)));
	}

	/**
	 * 刪除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int sort, String id) throws Exception {
		StringBuffer sb = new StringBuffer("UPDATE " + TABLE_NAME);
		sb.append(" SET SORT = SORT-1  WHERE SORT >= ?");
		Object[] args = { sort };
		simpleJdbc.update(sb.toString(), args);

		StringBuffer sb1 = new StringBuffer("DELETE FROM " + TABLE_NAME + " WHERE ID=? ");
		Object[] args1 = { id };
		simpleJdbc.update(sb1.toString(), args1);
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void insert(MajorFunction entity) throws Exception {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(entity));
	}

	/**
	 * 編輯
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void update(MajorFunction entity) throws Exception {
		String sql = "UPDATE " + TABLE_NAME
				+ " SET NAME=?, COLOR=?, CURSOR_COLOR=?, SHOW_ICON=?, STATUS=?, TYPE=?,LINK=?, AD_NAME=?, IMAGE=?, AD_PROPOSAL=?, MODIFIER=?, MODIFY_TIME=? WHERE ID=?";
		Object[] args = { entity.getName(), entity.getColor(), entity.getCursor_color(), entity.getShow_icon(),
				entity.getStatus(), entity.getType(), entity.getLink(), entity.getAd_name(), entity.getImage(), entity.getAd_proposal(), entity.getModifier(), entity.getModify_time(),
				entity.getId() };
		simpleJdbc.update(sql, args);
	}

	/**
	 * 更新狀態
	 * 
	 * @param id
	 * @param status
	 * @param modifier
	 * @throws Exception
	 */
	public void updateStatus(String id, String status, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(TABLE_NAME);
		sb.append(" SET STATUS=?, MODIFIER=?, MODIFY_TIME=? WHERE ID = ?");
		Object[] args = { status, modifier, now, id };
		simpleJdbc.update(sb.toString(), args);
	}

	/**
	 * 更新排序
	 * 
	 * @param id
	 * @param sort
	 * @param modifier
	 * @throws Exception
	 */
	public void updateSort(String id, int initSort, int sort, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		MajorFunction temp = this.getBySort(sort);

		if (temp != null) {
			StringBuffer sb = new StringBuffer("UPDATE ");
			sb.append(TABLE_NAME);
			sb.append(" SET SORT=? WHERE SORT = ?");
			Object[] args = { initSort , sort};
			simpleJdbc.update(sb.toString(), args);
		}
		StringBuffer sb1 = new StringBuffer("UPDATE ");
		sb1.append(TABLE_NAME);
		sb1.append(" SET SORT=?, MODIFIER=?, MODIFY_TIME=? WHERE ID = ?");
		Object[] args1 = { sort, modifier, now, id };
		simpleJdbc.update(sb1.toString(), args1);
	}

	/**
	 * 依sort取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MajorFunction getBySort(int sort) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE SORT=?");
		Object[] args = { sort };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(MajorFunction.class)));
	}


}
