package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddim.happygo.dao.ManagerRoleTaskDao;
import com.ddim.happygo.model.ManagerRoleTask;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.service<P> 
 * 類別名稱：ManagerRoleTaskService.java<P>
 * 程式內容說明：後台角色功能對應服務<P>
 * @author Yvonne
 */
@Service
public class ManagerRoleTaskService {

	@Autowired
	private ManagerRoleTaskDao managerRoleTaskDao;

	/**
	 * 刪除
	 * 
	 * @param role_id
	 * @throws Exception
	 */
	public void delByRole(String role_id) throws Exception {
		managerRoleTaskDao.delByRole(role_id);
	}

	/**
	 * 新增
	 * 
	 * @param roleTask
	 * @param modifier
	 * @throws Exception
	 */
	public void insert(ManagerRoleTask entity, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		entity.setCreator(modifier);
		entity.setCreate_time(now);
		managerRoleTaskDao.insert(entity);
	}

}
