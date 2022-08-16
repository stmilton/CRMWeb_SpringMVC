/*
      Created on : 2012/8/20
 */
package com.ddim.happygo.web.admin.tinymce;

import org.springframework.validation.Errors;

/**
 * 建立日期：2015年3月18日
 * 程式摘要：com.ddim.happygo.web.tinymce<P> 
 * 類別名稱：UploadImgFormValidator.java<P>
 * 程式內容說明：編輯器上傳圖檔表單 驗證<P>
 * @author Ringo
 * */
public class UploadImgFormValidator {

	public void validate(UploadImgForm form, Errors errors) {
		if (form.getFile() != null && !form.getFile().isEmpty()) {
			if (form.getFile().getContentType().indexOf("image/") < 0) {
				errors.rejectValue("file", "error", "上傳的內容必須是圖片!");
			}
		} else {
			errors.rejectValue("file", "error", "請選擇圖檔上傳!");
		}
	}
}
