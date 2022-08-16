package com.ddim.happygo.web.admin.role;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


/**
 * 建立日期：2015年3月18日
 * 程式摘要：com.ddim.happygo.web.role<P> 
 * 類別名稱：RoleFormValidator.java<P>
 * 程式內容說明：角色資料 驗證<P>
 * @author Yvonne
 * */
@Component
public class RoleFormValidator {

	/**
	 * 表單驗證
	 * 
	 * @param errors
	 */
	public void validate(RoleForm form, BindingResult result) {
		if (StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請輸入");
		}
		if (form.getTasksAry() == null || form.getTasksAry().length == 0) {
			result.rejectValue("tasksAry", "error", "請選擇");
		}
		if (StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}
	}

}