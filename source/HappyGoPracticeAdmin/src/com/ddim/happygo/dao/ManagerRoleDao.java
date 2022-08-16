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

import com.ddim.happygo.model.ManagerRole;
import com.mdbs.jdbc.ClassRowMapperFactory;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2016/01/15
 * 程式摘要：com.ddim.happygo.dao<P>
 * 類別名稱：ManagerRoleDao.java<P>
 * 程式內容說明：後台角色資料表<P>
 * @author Yvonne
 * */

@Repository
public class ManagerRoleDao {

	@Autowired
	private SimpleJdbc simpleJdbc;
	public final static String TABLE_NAME = "HG_MANAGER_ROLE";
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void init(DataSource dataSource) throws Exception {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingColumns("ID", "NAME",
				"STATUS", "CREATOR", "CREATE_TIME", "MODIFIER", "MODIFY_TIME");
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
	public Page<ManagerRole> findPage(String name, String status, PagingParameter paging) throws Exception {
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
		sb.append(" ORDER BY CREATE_TIME DESC");
		return simpleJdbc.findPage(sb.toString(), args.toArray(), paging, ClassRowMapperFactory.get(ManagerRole.class));
	}

	/**
	 * 查詢角色列表
	 * 
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<ManagerRole> getList(String status) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT * FROM " + TABLE_NAME + " WHERE ID IS NOT NULL");
		List<Object> args = new ArrayList<Object>();
		if (StringUtils.isNotBlank(status)) {
			sql.append(" AND STATUS = ?");
			args.add(status);
		}
		sql.append(" ORDER BY CREATE_TIME DESC");
		return simpleJdbc.query(sql.toString(), args.toArray(), ClassRowMapperFactory.get(ManagerRole.class));
	}

	/**
	 * 依 ID 查詢角色
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ManagerRole get(String id) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID=?");
		Object[] args = { id };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(ManagerRole.class)));
	}

	/**
	 * 刪除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(String id) throws Exception {
		StringBuffer sb = new StringBuffer("DELETE FROM " + TABLE_NAME + " WHERE ID=? ");
		Object[] args = { id };
		simpleJdbc.update(sb.toString(), args);
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void insert(ManagerRole entity) throws Exception {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(entity));
	}

	/**
	 * 編輯
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public void update(ManagerRole entity) throws Exception {
		String sql = "UPDATE " + TABLE_NAME + " SET NAME=?, STATUS=?, MODIFIER=?, MODIFY_TIME=? WHERE ID=?";
		Object[] args = { entity.getName(), entity.getStatus(), entity.getModifier(), entity.getModify_time(),
				entity.getId() };
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
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(TABLE_NAME);
		sb.append(" SET STATUS=?, MODIFIER=?, MODIFY_TIME=? WHERE ID=?");
		Object[] args = { status, modifier, now, id };
		simpleJdbc.update(sb.toString(), args);
	}

}
