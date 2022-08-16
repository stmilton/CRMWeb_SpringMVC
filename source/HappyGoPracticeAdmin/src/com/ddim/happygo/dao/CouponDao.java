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

import com.ddim.happygo.model.Coupon;
import com.mdbs.jdbc.ClassRowMapperFactory;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2016/01/15 程式摘要：com.ddim.happygo.dao
 * <P>
 * 類別名稱：CouponDao.java
 * <P>
 * 程式內容說明：最新消息連結資料表
 * <P>
 * 
 * @author Yvonne
 */
@Repository
public class CouponDao {

	@Autowired
	private SimpleJdbc simpleJdbc;
	public final static String TABLE_NAME = "HG_COUPON";
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void init(DataSource dataSource) throws Exception {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingColumns("ID", "AD_NAME",
				"START_TIME", "END_TIME", "STATUS", "IMAGE", "LINK", "SORT", "CREATOR", "CREATE_TIME",
				"MODIFIER", "MODIFY_TIME");
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
	public Page<Coupon> findPage(String name, String status, PagingParameter paging) throws Exception {
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
		return simpleJdbc.findPage(sb.toString(), args.toArray(), paging, ClassRowMapperFactory.get(Coupon.class));
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
	 * 依ID取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Coupon get(String id) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID=?");
		Object[] args = { id };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(Coupon.class)));
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
	public void insert(Coupon entity) throws Exception {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(entity));
	}

	/**
	 * 編輯
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void update(Coupon entity) throws Exception {
		String sql = "UPDATE " + TABLE_NAME
				+ " SET AD_NAME=?, START_TIME=?, END_TIME=?, STATUS=?, IMAGE=?, LINK=?, MODIFIER=?, MODIFY_TIME=? WHERE ID=?";
		Object[] args = { entity.getAd_name(), entity.getStart_time(), entity.getEnd_time(), entity.getStatus(),
				entity.getImage(), entity.getLink(), entity.getModifier(), entity.getModify_time(),
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
		Coupon temp = this.getBySort(sort);

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
	public Coupon getBySort(int sort) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE SORT=?");
		Object[] args = { sort };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(Coupon.class)));
	}

}
