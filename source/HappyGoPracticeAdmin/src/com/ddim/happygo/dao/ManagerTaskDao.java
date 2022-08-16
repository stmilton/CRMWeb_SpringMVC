package com.ddim.happygo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ddim.happygo.model.ManagerTask;
import com.mdbs.jdbc.ClassRowMapperFactory;
import com.mdbs.jdbc.SimpleJdbc;

/***
 * 建立日期：2016/01/15
 * 程式摘要：com.ddim.happygo.dao<P>
 * 類別名稱：ManagerTaskDao.java<P>
 * 程式內容說明：帳號角色資料表<P>
 * @author Yvonne
 * */

@Repository
public class ManagerTaskDao {

	@Autowired
	private SimpleJdbc simpleJdbc;
	public final static String TABLE_NAME = "HG_MANAGER_TASK";
	public final static String TABLE_NAME_MRT = "HG_MANAGER_ROLE_TASK";

	/**
	 * 查詢父功能列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ManagerTask> getParents() throws Exception {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM " + TABLE_NAME + " WHERE PARENT_ID IS NULL AND STATUS=1 ORDER BY SORT");
		return simpleJdbc.query(sql.toString(), null, ClassRowMapperFactory.get(ManagerTask.class));
	}

	/**
	 * 依PARENT_ID查詢子功能列表
	 * 
	 * @param parent
	 * @return List<ManagerTask>
	 * @throws Exception
	 */
	public List<ManagerTask> getChilds(String parent_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM " + TABLE_NAME + " WHERE ID IS NOT NULL AND STATUS=1");
		List<Object> args = new ArrayList<Object>();
		if (StringUtils.isNotBlank(parent_id)) {
			sql.append(" AND PARENT_ID = ?");
			args.add(parent_id);
		}
		sql.append(" ORDER BY SORT");
		return simpleJdbc.query(sql.toString(), args.toArray(), ClassRowMapperFactory.get(ManagerTask.class));
	}

	/**
	 * 依role_id查詢父功能列表
	 * 
	 * @param role_id
	 * @return List<ManagerTask>
	 * @throws Exception
	 */
	public List<ManagerTask> getParentTaskByRole(String role_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM " + TABLE_NAME);
		sql.append(" WHERE STATUS=1 AND ID IN");
		sql.append(" (");
		sql.append(" SELECT T.PARENT_ID FROM " + TABLE_NAME_MRT + " RT");
		sql.append(" LEFT JOIN " + TABLE_NAME + " T ON T.ID = RT.TASK_ID");
		sql.append(" WHERE RT.ROLE_ID IS NOT NULL AND RT.ROLE_ID = ? GROUP BY T.PARENT_ID");
		sql.append(" )");
		sql.append(" ORDER BY SORT");
		List<Object> args = new ArrayList<Object>();
		args.add(role_id);
		return simpleJdbc.query(sql.toString(), args.toArray(), ClassRowMapperFactory.get(ManagerTask.class));
	}

	/**
	 * 依role_id查詢子功能列表
	 * 
	 * @param role_id
	 * @param parent
	 * @param groupBy
	 * @return List<ManagerTask>
	 * @throws Exception
	 */
	public List<ManagerTask> getChildTaskByRole(String role_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from " + TABLE_NAME_MRT + " rt");
		sql.append(" left join " + TABLE_NAME + " t on t.id = rt.task_id where rt.role_id is not null");
		List<Object> args = new ArrayList<Object>();
		sql.append(" and rt.role_id = ?");
		args.add(role_id);
		sql.append(" order by t.sort");
		return simpleJdbc.query(sql.toString(), args.toArray(), ClassRowMapperFactory.get(ManagerTask.class));
	}

	/**
	 * 依parent_id及role_id查詢子功能列表(登入使用)
	 * 
	 * @param parent_id
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	public List<ManagerTask> getLoginChilds(String parent_id, String role_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MT.* FROM " + TABLE_NAME_MRT + " MRT");
		sql.append(" INNER JOIN " + TABLE_NAME + " MT ON MT.ID = MRT.TASK_ID");
		sql.append(" WHERE MT.ID IS NOT NULL AND MT.STATUS=1");
		List<Object> args = new ArrayList<Object>();
		if (StringUtils.isNotBlank(parent_id)) {
			sql.append(" AND MT.PARENT_ID = ?");
			args.add(parent_id);
		}
		if (StringUtils.isNotBlank(role_id)) {
			sql.append(" AND MRT.ROLE_ID = ?");
			args.add(role_id);
		}
		sql.append(" ORDER BY MT.SORT");
		return simpleJdbc.query(sql.toString(), args.toArray(), ClassRowMapperFactory.get(ManagerTask.class));
	}

	/**
	 * 依ID取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ManagerTask get(String id) throws Exception {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(TABLE_NAME);
		sb.append(" WHERE ID = ?");
		Object[] args = { id };
		return simpleJdbc.getFirst(simpleJdbc.query(sb.toString(), args, ClassRowMapperFactory.get(ManagerTask.class)));
	}

}
