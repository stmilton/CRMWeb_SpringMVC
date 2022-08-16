package com.ddim.happygo.web.admin.majorFunction;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ddim.happygo.Constants;
import com.ddim.happygo.MessageConstants;
import com.ddim.happygo.model.MajorFunction;
import com.ddim.happygo.service.MajorFunctionService;
import com.ddim.happygo.util.ManagerUtil;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.util.LogUtil;

/**
 * 建立日期：2015年3月7日 程式摘要：com.ddim.happygo.web.admin.majorFunction
 * <P>
 * 類別名稱：MajorFunctionController.java
 * <P>
 * 程式內容說明：最新消息控制項
 * <P>
 * 
 * @author Yvonne
 */
@Controller
@RequestMapping(value = "/admin/majorfunction")
public class MajorFunctionController {
	@Value("${page_size}")
	private int pageItems;
	@Autowired
	private MajorFunctionService majorFunctionService;
	@Autowired
	private ManagerUtil managerUtil;
	@Autowired
	private MajorFunctionFormValidator formValidator;

	@Resource(name = "statusOption")
	private Map<String, String> statusOption;

	@Resource(name = "typeOption")
	private Map<String, String> typeOption;

	@Resource(name = "iconOption")
	private Map<String, String> iconOption;

	@Value("${file.read.url}")
	private String fileReadUrl;

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Value("${majorfunction.path}")
	private String majorFunctionPath;

	protected String getRightID() {
		return Constants.MAIN_MENU_TASK_KEY;
	}

	protected String getPath() {
		return "admin/majorFunction";
	}

	final String[] DISALLOWED_FIELDS = new String[] { "dummy" };

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setDisallowedFields(DISALLOWED_FIELDS);
	}

	private void setCommonData(HttpServletRequest request) throws Exception {
		request.setAttribute("statusOption", statusOption);
		request.setAttribute("typeOption", typeOption);
		request.setAttribute("iconOption", iconOption);
		request.setAttribute("imagePath", fileReadUrl + majorFunctionPath);
		request.setAttribute("countList", majorFunctionService.countList());
	}

	/**
	 * 列表
	 * 
	 * @param request
	 * @param form
	 * @param paging
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")

	public String list(HttpServletRequest request, @ModelAttribute("command") MajorFunctionForm form,
			PagingParameter paging, ModelMap model) throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			setCommonData(request);
			paging.setPageSize(pageItems);
			Page<MajorFunction> page = majorFunctionService.findPage(form.getName(), form.getStatus(), paging);
			model.addAttribute("page", page);
			return getPath() + "/list";
		} catch (Exception e) {
			LogUtil.setErrorLog("list", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 編輯
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, @ModelAttribute("command") MajorFunctionForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			if (StringUtils.isNotBlank(form.getId())) {
				MajorFunction data = majorFunctionService.get(form.getId());
				if (data == null) {
					model.addAttribute("message", MessageConstants.MESSAGE_NO_DATA);
					model.addAttribute("relativeUrl", "list.do");
					return "common/jsAlert";
				}
				BeanUtils.copyProperties(data, form);
			}
			setCommonData(request);
			return getPath() + "/edit";
		} catch (Exception e) {
			LogUtil.setErrorLog("edit", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 存檔
	 * 
	 * @param request
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, @ModelAttribute("command") MajorFunctionForm form,
			BindingResult result, ModelMap model) throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			formValidator.validate(form, result, fileUploadPath + majorFunctionPath);
			if (result.hasErrors()) {
				setCommonData(request);
				return getPath() + "/edit";
			}
			MajorFunction entity = new MajorFunction();
			BeanUtils.copyProperties(form, entity);
			majorFunctionService.save(entity, managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request), "save majorFunction success: " + entity.getId());
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("save", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 更新狀態
	 * 
	 * @param request
	 * @param ids
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @ModelAttribute("command") MajorFunctionForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			majorFunctionService.updateStatus(form.getIds(), form.getStatus(), managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"update majorFunction status success: " + form.getIds());
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("update", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 刪除
	 * 
	 * @param request
	 * @param ids
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @ModelAttribute("command") MajorFunctionForm form, ModelMap model)
			throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			majorFunctionService.delete(form.getIds(), managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"delete majorFunction success: " + form.getIds());
			model.addAttribute("message", MessageConstants.MESSAGE_DELETE_SUCCESS);
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("delete", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

	/**
	 * 變更排序
	 * 
	 * @param request
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSort")
	public String updateSort(HttpServletRequest request, @ModelAttribute("command") MajorFunctionForm form,
			ModelMap model) throws Exception {
		try {
			String returnPath = managerUtil.haveRight(request, getRightID());
			if (returnPath != null) {
				return returnPath;
			}
			majorFunctionService.updateSort(form.getId(), form.getSort(), managerUtil.getManagerAccount(request));
			LogUtil.setSaveLog(managerUtil.getManagerAccount(request),
					"update majorFunction sort success: " + form.getIds());
			model.addAttribute("message", "更新排序成功!");
			model.addAttribute("relativeUrl", "list.do");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("updateSort", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INDEX_URI);
			return "common/jsAlert";
		}
	}

}
