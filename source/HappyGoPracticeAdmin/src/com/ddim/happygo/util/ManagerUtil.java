package com.ddim.happygo.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ddim.happygo.MessageConstants;
import com.ddim.happygo.model.Manager;
import com.ddim.happygo.model.ManagerTask;
import com.ddim.happygo.service.ManagerTaskService;

/**
 * 建立日期：2015年3月9日
 * 程式摘要：com.ddim.happygo.util<P> 
 * 類別名稱：ManagerUtil.java<P>
 * 程式內容說明：控管權限Util<P>
 * @author Yvonne
 */
@Service
public class ManagerUtil {

	public static String MANAGER_SESSION_KEY = "manager";
	public static String MANAGER_MENU_SESSION_KEY = "menuTasks";
	private static ManagerUtil instance = null;

	public static ManagerUtil createInstance() {
		instance = new ManagerUtil();
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	public static ManagerUtil getInstance() {
		return instance;
	}

	private ManagerUtil() {
	}

	/**
	 * 取的session中管理者資訊
	 * 
	 * @param request
	 * @return
	 */
	public Manager findManager(HttpServletRequest request) {
		return (Manager) request.getSession().getAttribute(MANAGER_SESSION_KEY);
	}

	/**
	 * 將管理者資訊放至session
	 * 
	 * @param request
	 * @param menuTasks
	 * @param user
	 */
	public void setManager(HttpServletRequest request, Manager manager, List<ManagerTask> menuTasks) {
		request.getSession().setAttribute(MANAGER_SESSION_KEY, manager);
		request.getSession().setAttribute(MANAGER_MENU_SESSION_KEY, menuTasks);
	}

	/**
	 * 清除session裡管理者資訊
	 * 
	 * @param request
	 */
	public void clearManagerLogin(HttpServletRequest request) {
		request.getSession().removeAttribute(MANAGER_SESSION_KEY);
		request.getSession().removeAttribute(MANAGER_MENU_SESSION_KEY);
	}

	/**
	 * 取得管理者資訊
	 * 
	 * @param request
	 * @return
	 */
	public Manager getManager(HttpServletRequest request) {
		return findManager(request);
	}

	/**
	 * 取得管理者account
	 * 
	 * @param request
	 * @return
	 */
	public String getManagerAccount(HttpServletRequest request) {
		return findManager(request).getAccount();
	}

	/**
	 * @param request
	 * @param rightID
	 * @return
	 * @throws Exception
	 */
	public String haveRight(HttpServletRequest request, String rightID) throws Exception {
		ManagerTask task = (StringUtils.isBlank(rightID)) ? null : checkRight(request, rightID);
		if (task == null) {
			request.setAttribute("message", MessageConstants.MESSAGE_AUTH_FAIL);
			request.setAttribute("relativeUrl", "/admin/index.do");
			return "common/jsAlert";
		} else {
			request.setAttribute("menuPath", getPathName(request, task));
			request.setAttribute("menuName", task.getName());
			request.setAttribute("menuLink", task.getUrl());
			request.setAttribute("rightID", rightID);
			return null;
		}
	}

	/**
	 * @param request
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public static ManagerTask checkRight(HttpServletRequest request, String taskId) throws Exception {
		List<ManagerTask> list = getMenu(request);
		return ManagerTaskService.findTask(list, taskId);
	}

	/**
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<ManagerTask> getMenu(HttpServletRequest request) {
		return (List<ManagerTask>) request.getSession().getAttribute(MANAGER_MENU_SESSION_KEY);
	}

	/**
	 * @param request
	 * @param task
	 * @return
	 */
	public static Object getPathName(HttpServletRequest request, ManagerTask task) {
		StringBuffer sb = new StringBuffer();
		if (task != null) {
			sb.append(task.getName());
			List<ManagerTask> list = getMenu(request);
			while (task != null && task.getParent_id() != null) {
				task = ManagerTaskService.findTask(list, task.getParent_id());
				sb.insert(0, task.getName() + " > ");
			}
		}
		return sb.toString();
	}
}
