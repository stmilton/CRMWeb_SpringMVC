package com.ddim.happygo.web.admin.majorFunction;

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
public class MajorFunctionFormValidator {

	/**
	 * 表單驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validate(MajorFunctionForm form, BindingResult result, String uploadFilePath) throws Exception {
		if (StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請輸入");
		}else if (form.getName().length() > 50) {
			result.rejectValue("name", "error", "輸入名稱過長");
		}

		if (StringUtils.isBlank(form.getColor())) {
			result.rejectValue("color", "error", "請輸入");
		}else if (!isHtmlColor(form.getColor())) {
			result.rejectValue("color", "error", "HTML色碼不合法");
		}

		if (StringUtils.isBlank(form.getCursor_color())) {
			result.rejectValue("cursor_color", "error", "請輸入");
		}else if (!isHtmlColor(form.getCursor_color())) {
			result.rejectValue("cursor_color", "error", "HTML色碼不合法");
		}

		if (StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}

		if (StringUtils.isBlank(form.getType())) {
			result.rejectValue("type", "error", "請選擇");
		}

		if (StringUtils.isBlank(form.getLink())) {
			result.rejectValue("link", "error", "請輸入");
		}else if (!ValidatorUtil.isValidUrl(form.getLink())) {
			result.rejectValue("link", "error", "請輸入合法網址");
		}

		if (StringUtils.isBlank(form.getAd_name())) {
			result.rejectValue("ad_name", "error", "請輸入");
		}else if (form.getAd_name().length() > 20) {
			result.rejectValue("ad_name", "error", "廣告名稱過長");
		}
		if (form.getImg_file() != null && !form.getImg_file().isEmpty()) {
			boolean IsContentType = false;
			if (form.getImg_file().getContentType().indexOf("image/") >= 0) {
				if (form.getImg_file().getContentType().indexOf("gif") >= 0) {
					IsContentType = true;
				}
				if (form.getImg_file().getContentType().indexOf("jpeg") >= 0) {
					IsContentType = true;
				}
				if (form.getImg_file().getContentType().indexOf("png") >= 0) {
					IsContentType = true;
				}
			}
			if (!IsContentType) {
				result.rejectValue("image", "error", "上傳的內容必須是圖片!");
			} else {
				form.setImage(FileUtil.getUploadFileName(form.getImg_file(), uploadFilePath));

			}
		} else if (StringUtils.isBlank(form.getImage())) {
			result.rejectValue("image", "error", "請上傳");
		}

		if (StringUtils.isBlank(form.getAd_proposal())) {
			result.rejectValue("ad_proposal", "error", "請輸入");
		}else if (form.getAd_proposal().length() > 150) {
			result.rejectValue("ad_proposal", "error", "廣告名稱過長");
		}
	}
	
	/**
	 * 驗證是否為合法HTML色碼
	 * 
	 * @param colorString
	 * @return
	 */
	private boolean isHtmlColor(String colorString) {
		
		//長度為7
		if (colorString.length() != 7) { 
			return false;
		}
		
		//起始為#字號
		if (!colorString.substring(0, 1).equals("#")) {
			return false;
		}
		
		Set<String> hexadecimal = new HashSet<String>(
				Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "a", "b", "c", "d", "e", "f"));
		
		for (int i = 1; i < colorString.length(); i++) {
			if (!hexadecimal.contains(colorString.substring(i, i + 1))) {
				return false;
			}
		}
		
		return true;
	}

}