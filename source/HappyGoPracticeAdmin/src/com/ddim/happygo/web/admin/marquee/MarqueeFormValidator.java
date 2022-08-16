package com.ddim.happygo.web.admin.marquee;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.mdbs.util.FileUtil;
import com.mdbs.util.ValidatorUtil;

/**
 * 建立日期：2015年3月18日
 * 程式摘要：com.ddim.happygo.web.admin.news<P> 
 * 類別名稱：NewsFormValidator.java<P>
 * 程式內容說明：最新消息資料 驗證<P>
 * @author Yvonne
 * */
@Component
public class MarqueeFormValidator {

	/**
	 * 表單驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validate(MarqueeForm form, BindingResult result, String uploadFilePath) throws Exception {
		if (StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請輸入");
		}else if (form.getName().length() > 100) {
			result.rejectValue("name", "error", "輸入名稱過長");
		}
		
		if (form.getStart_time() == null) {
			result.rejectValue("start_time", "error", "請輸入");
		}
		
		if (form.getEnd_time() == null) {
			result.rejectValue("end_time", "error", "請輸入");
		}
		if (form.getStart_time() != null && form.getEnd_time() != null) {
			if (form.getEnd_time().before(form.getStart_time())) {
				result.rejectValue("end_time", "error", "結束時間不可早於起始時間");
			}
		}
		if (StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}
		
		if (StringUtils.isBlank(form.getLink())) {
			result.rejectValue("link", "error", "請輸入");
		}else if(!ValidatorUtil.isValidUrl(form.getLink())) {
			result.rejectValue("link", "error", "請輸入合法網址");
		}
		if (StringUtils.isBlank(form.getOpen_method())) {
			result.rejectValue("open_method", "error", "請選擇");
		}
	}
}