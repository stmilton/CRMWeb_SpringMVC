package com.ddim.happygo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddim.happygo.dao.ManagerTaskDao;
import com.ddim.happygo.model.ManagerTask;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.service<P> 
 * 類別名稱：ManagerTaskService.java<P>
 * 程式內容說明：後台功能項目服務<P>
 * @author Yvonne
 */
@Service
public class ManagerTaskService {

	@Autowired
	private ManagerTaskDao managerTaskDao;

	/**
	 * 查詢所有功能列表
	 * 
	 * @return List<Task>
	 * @throws Exception
	 */
	public List<ManagerTask> getParents() throws Exception {
		List<ManagerTask> list = managerTaskDao.getParents();
		for (ManagerTask entity : list) {
			List<ManagerTask> childs = managerTaskDao.getChilds(entity.getId());
			if (childs != null && childs.size() > 0) {
				entity.setChilds(childs);
			}
		}
		return list;
	}

	/**
	 * 查詢功能列表(登入使用)
	 * 
	 * @param role_id
	 * @return List<Task>
	 * @throws Exception
	 */
	public List<ManagerTask> getLoginTask(String role_id) throws Exception {
		// 查詢該角色擁有之功能父選單
		List<ManagerTask> list = managerTaskDao.getParentTaskByRole(role_id);
		for (ManagerTask entity : list) {
			List<ManagerTask> childs = managerTaskDao.getLoginChilds(entity.getId(), role_id);
			entity.setChilds(childs);
		}
		return list;
	}

	/**
	 * 查詢功能(編號)
	 * 
	 * @param id
	 * @return Task
	 * @throws Exception
	 */
	public ManagerTask get(String id) throws Exception {
		return managerTaskDao.get(id);
	}

	/**
	 * 查詢該角色功能列表
	 * 
	 * @param role_id
	 * @return List<Task>
	 * @throws Exception
	 */
	public List<ManagerTask> getChildTaskByRole(String role_id) throws Exception {
		List<ManagerTask> result = managerTaskDao.getChildTaskByRole(role_id);
		return result;
	}

	/**
	 * 比對功能
	 * 
	 * @param parentTasks
	 * @param id
	 * @return Task
	 */
	public static ManagerTask findTask(List<ManagerTask> parentTasks, String id) {
		for (ManagerTask parentTask : parentTasks) {
			if (parentTask.getId().equals(id)) {
				return parentTask;
			} else {
				if (parentTask.getChilds() != null) {
					ManagerTask task = findTask(parentTask.getChilds(), id);
					if (task != null)
						return task;
				}
			}
		}
		return null;
	}
}
