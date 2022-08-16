package com.ddim.happygo.web.admin.news;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.mdbs.util.FileUtil;

/**
 * 建立日期：2015年3月18日
 * 程式摘要：com.ddim.happygo.web.admin.news<P> 
 * 類別名稱：NewsFormValidator.java<P>
 * 程式內容說明：最新消息資料 驗證<P>
 * @author Yvonne
 * */
@Component
public class NewsFormValidator {

	/**
	 * 表單驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validate(NewsForm form, BindingResult result, String uploadFilePath) throws Exception {
		if (StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請輸入");
		}
		if (StringUtils.isBlank(form.getDescription())) {
			result.rejectValue("description", "error", "請輸入");
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
	}

}