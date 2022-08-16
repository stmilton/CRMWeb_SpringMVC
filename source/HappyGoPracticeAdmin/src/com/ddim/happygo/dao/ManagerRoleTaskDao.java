package com.ddim.happygo.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ddim.happygo.model.ManagerRoleTask;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2016/01/15
 * 程式摘要：com.ddim.happygo.dao<P>
 * 類別名稱：ManagerRoleDao.java<P>
 * 程式內容說明：後台功能所屬角色資料表<P>
 * @author Yvonne
 * */

@Repository
public class ManagerRoleTaskDao {

	@Autowired
	private SimpleJdbc simpleJdbc;
	public final static String TABLE_NAME = "HG_MANAGER_ROLE_TASK";
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void init(DataSource dataSource) throws Exception {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingColumns("ROLE_ID", "TASK_ID",
				"CREATOR", "CREATE_TIME");
	}

	/**
	 * 依ID刪除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delByRole(String role_id) throws Exception {
		StringBuffer sb = new StringBuffer("DELETE FROM " + TABLE_NAME + " WHERE ROLE_ID=? ");
		Object[] args = { role_id };
		simpleJdbc.update(sb.toString(), args);
	}

	/**
	 * 新增ManagerRoleTask物件
	 * 
	 * @param ManagerRole
	 * @throws Exception
	 */
	public void insert(ManagerRoleTask entity) throws Exception {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(entity));
	}

}
