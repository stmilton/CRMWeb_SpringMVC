package com.ddim.happygo.web.admin.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.ddim.happygo.model.Manager;
import com.ddim.happygo.service.ManagerService;
import com.mdbs.util.ValidatorUtil;

/**
 * 建立日期：2015年3月18日
 * 程式摘要：com.ddim.happygo.web.admin.manager<P> 
 * 類別名稱：ManagerFormValidator.java<P>
 * 程式內容說明：管理者表單資料 驗證<P>
 * @author Yvonne
 *
 */
@Component
public class ManagerFormValidator {

	@Autowired
	private ManagerService managerService;

	/**
	 * 表單驗證
	 * 
	 * @param form
	 * @param result
	 * @param managerService
	 * @throws Exception
	 */
	public void validate(ManagerForm form, BindingResult result) throws Exception {
		if (StringUtils.isBlank(form.getAccount())) {
			result.rejectValue("account", "error", "請輸入");
		} else {
			if (form.getAccount().length() < 4) {
				result.rejectValue("account", "error", "帳號不得少於4個字元");
			} else if (!ValidatorUtil.isValidAccount(form.getAccount())) {
				result.rejectValue("account", "error", "帳號只能輸入英文或數字");
			} else {
				Manager data = managerService.getByAccount(form.getAccount());
				if (data != null && !data.getId().equals(form.getId())) {
					result.rejectValue("account", "error", "此帳號已被註冊");
				}
			}
		}
		boolean emptyPassword = StringUtils.isBlank(form.getNewPwd());
		if (StringUtils.isBlank(form.getId())) {
			if (emptyPassword) {
				result.rejectValue("pwd", "error", "請輸入");
			}
		}
		if (!emptyPassword) {
			if (form.getNewPwd().length() < 4) {
				result.rejectValue("pwd", "error", "密碼不可少於4個字元");
			}
			if (!ValidatorUtil.isPassword(form.getPwd())) {
				result.rejectValue("pwd", "error", "密碼只能輸入英文或數字");
			}

			if (form.getPwd().equals(form.getAccount())) {
				result.rejectValue("pwd", "error", "密碼不得與帳號相同");
			}
		}

		if (StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請輸入");
		}
		if (StringUtils.isBlank(form.getEmail())) {
			result.rejectValue("email", "error", "請輸入");
		} else {
			if (!ValidatorUtil.isValidEmail(form.getEmail())) {
				result.rejectValue("email", "error", "格式不正確");
			}
		}
		if (StringUtils.isBlank(form.getMobile())) {
			result.rejectValue("mobile", "error", "請輸入");
		} else {
			if (!ValidatorUtil.isValidMobile(form.getMobile())) {
				result.rejectValue("mobile", "error", "格式不正確");
			}
		}
		if (StringUtils.isBlank(form.getTel())) {
		} else {
			if (!ValidatorUtil.isValidPhone(form.getTel())) {
				result.rejectValue("tel", "error", "電話只能輸入數字");
			}
		}
		if (StringUtils.isBlank(form.getRole_id())) {
			result.rejectValue("role_id", "error", "請選擇");
		}
		if (StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}
	}
}
