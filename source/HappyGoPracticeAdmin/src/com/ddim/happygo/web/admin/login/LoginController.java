package com.ddim.happygo.web.admin.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ddim.happygo.model.Manager;
import com.ddim.happygo.model.ManagerRole;
import com.ddim.happygo.model.ManagerTask;
import com.ddim.happygo.service.ManagerRoleService;
import com.ddim.happygo.service.ManagerService;
import com.ddim.happygo.service.ManagerTaskService;
import com.ddim.happygo.util.ManagerUtil;
import com.mdbs.util.LogUtil;
import com.mdbs.util.WebUtil;

/**
 * 建立日期：2015年3月7日
 * 程式摘要：com.ddim.happygo.web.admin.login<P> 
 * 類別名稱：LoginController.java<P>
 * 程式內容說明：管理者登入控制項<P>
 * @author Yvonne
 * */
@Controller
public class LoginController {
	@Autowired
	private ManagerUtil managerUtil;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ManagerTaskService managerTaskService;
	@Autowired
	private ManagerRoleService managerRoleService;

	final String[] DISALLOWED_FIELDS = new String[] { "dummy" };

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setDisallowedFields(DISALLOWED_FIELDS);
	}

	/**
	 * 後台首頁
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/index")
	public String index() {
		return "admin/index";
	}

	/**
	 * 登入呈現頁面
	 * 
	 * @param request
	 * @param command
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/login")
	public String login(HttpServletRequest request) {
		managerUtil.clearManagerLogin(request);
		return "admin/login";
	}

	/**
	 * 登入
	 * 
	 * @param request
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @ModelAttribute("command") LoginForm form, Model model)
			throws Exception {
		try {
			Manager manager = null;
			manager = managerService.login(form.getAccount(), form.getPwd());
			if (manager == null) {
				model.addAttribute("message", "輸入帳號密碼錯誤!");
				model.addAttribute("relativeUrl", "login.do");
				return "common/jsAlert";
			}
	
			if (!"1".equals(manager.getStatus())) {
				model.addAttribute("message", "此帳號已被停權!");
				model.addAttribute("relativeUrl", "login.do");
				return "common/jsAlert";
			}
			
			ManagerRole role = managerRoleService.get(manager.getRole_id());
			
			if (role == null || !"1".equals(role.getStatus())) {
				model.addAttribute("message", "此帳號無權限進入!");
				model.addAttribute("relativeUrl", "login.do");
				return "common/jsAlert";
			}
			// 判斷登入者權限使用
			LogUtil.setActionLog("LOGIN", "login success: " + form.getAccount());
			List<ManagerTask> menuTasks = managerTaskService.getLoginTask(manager.getRole_id());
			managerUtil.setManager(request, manager, menuTasks);
		} catch (Exception e) {
			LogUtil.setErrorLog("login", e);
		}
		return WebUtil.getRedirect("index.do");
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/logout")
	public String logout(HttpServletRequest request) {
		managerUtil.clearManagerLogin(request);
		return "redirect:login.do";
	}

}
