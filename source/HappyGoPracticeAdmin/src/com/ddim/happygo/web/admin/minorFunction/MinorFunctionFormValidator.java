package com.ddim.happygo.web.admin.minorFunction;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.mdbs.util.FileUtil;
import com.mdbs.util.ValidatorUtil;
import com.mdbs.web.tag.stringBreakToBrTag;

/**
 * 建立日期：2015年3月18日 程式摘要：com.ddim.happygo.web.admin.news
 * <P>
 * 類別名稱：NewsFormValidator.java
 * <P>
 * 程式內容說明：最新消息資料 驗證
 * <P>
 * 
 * @author Yvonne
 */
@Component
public class MinorFunctionFormValidator {

	/**
	 * 表單驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validate(MinorFunctionForm form, BindingResult result, String uploadFilePath) throws Exception {
		if (StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請輸入");
		} else if (form.getName().length() > 10) {
			result.rejectValue("name", "error", "輸入名稱過長");
		}

		if (StringUtils.isBlank(form.getMajor_name())) {
			result.rejectValue("major_name", "error", "請選擇");
		}

		if (StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}

		if (StringUtils.isBlank(form.getLink())) {
			result.rejectValue("link", "error", "請輸入");
		} else if (!ValidatorUtil.isValidUrl(form.getLink())) {
			result.rejectValue("link", "error", "請輸入合法網址");
		}

		if (StringUtils.isBlank(form.getOpen_method())) {
			result.rejectValue("open_method", "error", "請選擇");
		}

	}
}