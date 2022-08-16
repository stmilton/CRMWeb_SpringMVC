package com.ddim.happygo.web.admin.specialDealer;

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
public class SpecialDealerFormValidator {

	/**
	 * 表單驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validate(SpecialDealerForm form, BindingResult result, String uploadFilePath) throws Exception {
		
		if (StringUtils.isBlank(form.getSpecial_dealer_code())) {
			result.rejectValue("special_dealer_code", "error", "請輸入");
		}else if (form.getSpecial_dealer_code().length() > 25) {
			result.rejectValue("special_dealer_code", "error", "輸入代碼過長");
		}
		
		if (StringUtils.isBlank(form.getHead_office_name())) {
			result.rejectValue("head_office_name", "error", "請輸入");
		}else if (form.getHead_office_name().length() > 25) {
			result.rejectValue("head_office_name", "error", "輸入名稱過長");
		}
		
		if (StringUtils.isBlank(form.getSpecial_dealer_name())) {
			result.rejectValue("special_dealer_name", "error", "請輸入");
		}else if (form.getSpecial_dealer_name().length() > 25) {
			result.rejectValue("special_dealer_name", "error", "輸入名稱過長");
		}
		
		if (StringUtils.isBlank(form.getSpecial_dealer_class())) {
			result.rejectValue("special_dealer_class", "error", "請選擇");
		}
		
		if (StringUtils.isBlank(form.getShow_address())) {
			result.rejectValue("show_address", "error", "請選擇");
		}
		
		if (StringUtils.isBlank(form.getShow_phone())) {
			result.rejectValue("show_phone", "error", "請選擇");
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
				
		if (form.getImage_app_file() != null && !form.getImage_app_file().isEmpty()) {
			boolean IsContentType = false;
			if (form.getImage_app_file().getContentType().indexOf("image/") >= 0) {
				if (form.getImage_app_file().getContentType().indexOf("gif") >= 0) {
					IsContentType = true;
				}
				if (form.getImage_app_file().getContentType().indexOf("jpeg") >= 0) {
					IsContentType = true;
				}
				if (form.getImage_app_file().getContentType().indexOf("png") >= 0) {
					IsContentType = true;
				}
			}
			if (!IsContentType) {
				result.rejectValue("image_app", "error", "上傳的內容必須是圖片!");
			} else {
				form.setImage_app(FileUtil.getUploadFileName(form.getImage_app_file(), uploadFilePath));
				
			}
		} else if (StringUtils.isBlank(form.getImage_app())) {
			result.rejectValue("image_app", "error", "請上傳");
		}
		
		if (form.getTop27_image_file() != null && !form.getTop27_image_file().isEmpty()) {
			boolean IsContentType = false;
			if (form.getTop27_image_file().getContentType().indexOf("image/") >= 0) {
				if (form.getTop27_image_file().getContentType().indexOf("gif") >= 0) {
					IsContentType = true;
				}
				if (form.getTop27_image_file().getContentType().indexOf("jpeg") >= 0) {
					IsContentType = true;
				}
				if (form.getTop27_image_file().getContentType().indexOf("png") >= 0) {
					IsContentType = true;
				}
			}
			if (!IsContentType) {
				result.rejectValue("top27_image", "error", "上傳的內容必須是圖片!");
			} else {
				form.setTop27_image(FileUtil.getUploadFileName(form.getTop27_image_file(), uploadFilePath));
				
			}
		}
				
		if (StringUtils.isBlank(form.getBasic_redemption_pc())) {
			result.rejectValue("basic_redemption_pc", "error", "請輸入");
		}else if (form.getBasic_redemption_pc().length() > 150) {
			result.rejectValue("basic_redemption_pc", "error", "輸入內容過長");
		}
		
		if (StringUtils.isBlank(form.getBasic_redemption_app())) {
			result.rejectValue("basic_redemption_app", "error", "請輸入");
		}else if (form.getBasic_redemption_app().length() > 150) {
			result.rejectValue("basic_redemption_app", "error", "輸入內容過長");
		}
		
		if (StringUtils.isBlank(form.getIs_show())) {
			result.rejectValue("is_show", "error", "請選擇");
		}
		
		if (StringUtils.isBlank(form.getMobile_phone_barcode())) {
			result.rejectValue("mobile_phone_barcode", "error", "請選擇");
		}		
		
		if (form.getContract_start_time() == null) {
			result.rejectValue("contract_start_time", "error", "請輸入");
		}		
		if (form.getContract_end_time() == null) {
			result.rejectValue("contract_end_time", "error", "請輸入");
		}
		if (form.getContract_start_time() != null && form.getContract_end_time() != null) {
			if (form.getContract_end_time().before(form.getContract_start_time())) {
				result.rejectValue("contract_end_time", "error", "結束時間不可早於起始時間");
			}
		}
		if(!ValidatorUtil.isValidUrl(form.getLink()) && !StringUtils.isBlank(form.getLink())) {
			result.rejectValue("link", "error", "請輸入合法網址");
		}
				
		if (StringUtils.isBlank(form.getOpen_method())) {
			result.rejectValue("open_method", "error", "請選擇");
		}
		
		if (StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}
	}
}