package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddim.happygo.Constants;
import com.ddim.happygo.dao.ManagerRoleDao;
import com.ddim.happygo.model.ManagerRole;
import com.ddim.happygo.model.ManagerRoleTask;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.util.Id;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.service<P> 
 * 類別名稱：ManagerRoleService.java<P>
 * 程式內容說明：後台角色友服務<P>
 * @author Yvonne
 */
@Service
public class ManagerRoleService {

	@Autowired
	private ManagerRoleTaskService roleTaskService;
	@Autowired
	private ManagerRoleDao managerRoleDao;


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
		return managerRoleDao.findPage(name, status, paging);
	}

	/**
	 * 查詢列表
	 * 
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<ManagerRole> getList(String status) throws Exception {
		return managerRoleDao.getList(status);
	}

	/**
	 * 取得單一物件
	 * 
	 * @param id
	 * @return ManagerRole
	 * @throws Exception
	 */
	public ManagerRole get(String id) throws Exception {
		return managerRoleDao.get(id);
	}

	/**
	 * 儲存，失敗時rollback
	 * 
	 * @param entity
	 * @param tasks
	 * @param modifier
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(ManagerRole entity, String[] tasks, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(Id.id20());
			entity.setCreator(modifier);
			entity.setCreate_time(now);
			entity.setModifier(modifier);
			entity.setModify_time(now);
			managerRoleDao.insert(entity);

		} else {
			entity.setModifier(modifier);
			entity.setModify_time(now);
			managerRoleDao.update(entity);
			roleTaskService.delByRole(entity.getId());

		}
		for (String task_id : tasks) {
			ManagerRoleTask roleTask = new ManagerRoleTask();
			roleTask.setRole_id(entity.getId());
			roleTask.setTask_id(task_id);
			roleTaskService.insert(roleTask, modifier);
		}
	}

	/**
	 * 刪除，失敗時rollback
	 * 
	 * @param ids
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(String[] ids, String modifier) throws Exception {
		for (String id : ids) {
			ManagerRole entity = get(id);
			if (!Constants.ADMIN_SN.equals(entity.getId())) {
				roleTaskService.delByRole(entity.getId());
				managerRoleDao.delete(entity.getId());
			}
		}
	}

	/**
	 * 更新狀態，失敗時rollback
	 * 
	 * @param ids
	 * @param status
	 * @param modifier
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateStatus(String[] ids, String status, String modifier) throws Exception {
		for (String id : ids) {
			ManagerRole entity = get(id);
			if (!Constants.ADMIN_SN.equals(entity.getId())) {
				managerRoleDao.updateStatus(entity.getId(), status, modifier);
			}
		}
	}

}
