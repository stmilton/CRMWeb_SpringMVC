package com.ddim.happygo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ddim.happygo.model.Manager;
import com.mdbs.jdbc.ClassRowMapperFactory;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2016/01/15
 * 程式摘要：com.ddim.happygo..dao<P>
 * 類別名稱：ManagerDao.java<P>
 * 程式內容說明：後台管理者連結管理者資料表<P>
 * @author Yvonne
 * */

@Repository
public class ManagerDao {

	@Autowired
	private SimpleJdbc simpleJdbc;
	public final static String TABLE_NAME = "HG_MANAGER";
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void init(DataSource dataSource) throws Exception {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingColumns("ID", "ACCOUNT",
				"PWD", "NAME", "EMAIL", "TEL", "MOBILE", "ROLE_ID", "STATUS", "CREATOR", "CREATE_TIME", "MODIFIER",
				"MODIFY_TIME");
	}

	/**
	 * 依使用者帳號及密碼取得管理者資訊
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public Manager login(String account, String pwd) throws Exception {
		Object[] args = { account, pwd };
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ACCOUNT=? AND PWD=?");
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(Manager.class)));
	}

	/**
	 * 取得 ID 取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Manager get(String id) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID = ?");
		Object[] args = { id };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(Manager.class)));
	}

	/**
	 * 取得帳號取得單一物件
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Manager getByAccount(String account) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ACCOUNT = ?");
		Object[] args = { account };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(Manager.class)));
	}

	/**
	 * 取得分頁
	 * 
	 * @param keyword
	 * @param status
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public Page<Manager> findPage(String keyword, String status, PagingParameter paging) throws Exception {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID IS NOT NULL");
		if (StringUtils.isNotBlank(keyword)) {
			sb.append(" AND NAME LIKE ? OR ACCOUNT LIKE ?");
			args.add("%" + keyword + "%");
			args.add("%" + keyword + "%");
		}
		if (StringUtils.isNotBlank(status)) {
			sb.append(" AND STATUS = ?");
			args.add(status);
		}
		sb.append(" ORDER BY CREATE_TIME DESC");
		return simpleJdbc.findPage(sb.toString(), args.toArray(), paging, ClassRowMapperFactory.get(Manager.class));
	}

	/**
	 * 新增Manager物件資訊
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void insert(Manager entity) throws Exception {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(entity));
	}

	/**
	 * 編輯Manager物件資訊
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void update(Manager entity) throws Exception {
		String sql = "UPDATE " + TABLE_NAME + " SET ACCOUNT=?, PWD=?, NAME=?, EMAIL=?, TEL=?, MOBILE=?, ROLE_ID=?"
				+ ", STATUS=?, MODIFIER=?, MODIFY_TIME=? WHERE ID=?";
		Object[] args = { entity.getAccount(), entity.getPwd(), entity.getName(), entity.getEmail(), entity.getTel(),
				entity.getMobile(), entity.getRole_id(), entity.getStatus(), entity.getModifier(),
				entity.getModify_time(), entity.getId() };
		simpleJdbc.update(sql, args);
	}

	/**
	 * 依 ID 更新狀態
	 * 
	 * @param id
	 * @param status
	 * @param modifier
	 * @throws Exception
	 */
	public void updateStatus(String id, String status, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		Object[] args = { now, modifier, status, id };
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(TABLE_NAME);
		sb.append(" SET MODIFY_TIME=?, MODIFIER=?, STATUS=? WHERE ID=?");
		simpleJdbc.update(sb.toString(), args);
	}

	/**
	 * 刪除Manager物件資訊
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception {
		StringBuffer sb = new StringBuffer("DELETE FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID= ?");
		Object[] args = { id };
		simpleJdbc.update(sb.toString(), args);
	}

}
