package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddim.happygo.Constants;
import com.ddim.happygo.dao.ManagerDao;
import com.ddim.happygo.model.Manager;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.util.Id;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.service<P> 
 * 類別名稱：ManagerService.java<P>
 * 程式內容說明：後台管理者服務<P>
 * @author Yvonne
 */
@Service
public class ManagerService {

	@Autowired
	private ManagerRoleService roleService;
	@Autowired
	private ManagerDao managerDao;


	/**
	 * 登入
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public Manager login(String account, String pwd) throws Exception {
		return managerDao.login(account, DigestUtils.md5Hex(pwd));
	}

	/**
	 * 取得單一物件
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Manager get(String id) throws Exception {
		return managerDao.get(id);
	}

	/**
	 * 取得單一物件
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Manager getByAccount(String account) throws Exception {
		return managerDao.getByAccount(account);
	}

	/**
	 * 確認是否為重覆帳號
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public boolean checkAccount(String account) throws Exception {
		boolean isDuplicateAccount = false;
		Manager entity = managerDao.getByAccount(account);
		if (entity != null) {
			isDuplicateAccount = true;
		}
		return isDuplicateAccount;
	}

	/**
	 * 分頁列表
	 * 
	 * @param keyword
	 * @param status
	 * @param paging
	 * @return
	 * @throws Exception
	 */
	public Page<Manager> findPage(String keyword, String status, PagingParameter paging) throws Exception {
		Page<Manager> pages = managerDao.findPage(keyword, status, paging);
		for (Manager entity : pages.getPageItems()) {
			entity.setRole(roleService.get(entity.getRole_id()));
		}
		return pages;
	}

	/**
	 * 儲存
	 * 
	 * @param entity
	 * @param modifier
	 * @throws Exception
	 */
	public void save(Manager entity, String modifier) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(Id.id20());
			entity.setStatus(entity.getStatus());
			entity.setCreator(modifier);
			entity.setCreate_time(now);
			entity.setModifier(modifier);
			entity.setModify_time(now);
			managerDao.insert(entity);
		} else {
			entity.setModifier(modifier);
			entity.setModify_time(now);
			managerDao.update(entity);
		}
	}

	/**
	 * 刪除
	 * 
	 * @param ids
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(String[] ids, String modifier) throws Exception {
		for (String id : ids) {
			Manager entity = get(id);
			if (!Constants.ADMIN_SN.equals(entity.getId())) {
				managerDao.delete(entity.getId());
			}
		}
	}

	/**
	 * 更新狀態
	 * 
	 * @param ids
	 * @param status
	 * @param modifier
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateStatus(String[] ids, String status, String modifier) throws Exception {
		for (String id : ids) {
			Manager entity = get(id);
			if (!Constants.ADMIN_SN.equals(entity.getId())) {
				managerDao.updateStatus(entity.getId(), status, modifier);
			}
		}
	}

}
