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
public class ExcelFormValidator {
	
	/**
	 * 表單驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validateContent(SpecialDealerForm form, StringBuilder validateMessage) throws Exception {
		
		if (StringUtils.isBlank(form.getSpecial_dealer_code())) {
			validateMessage.append("特約商代碼不能空白\n");
		}else if (form.getSpecial_dealer_code().length() > 25) {
			validateMessage.append("特約商代碼過長\n");
		}
		
		if (StringUtils.isBlank(form.getHead_office_name())) {
			validateMessage.append("總公司名稱不能空白\n");
		}else if (form.getHead_office_name().length() > 25) {
			validateMessage.append("總公司名稱過長\n");
		}
		
		if (StringUtils.isBlank(form.getSpecial_dealer_name())) {
			validateMessage.append("特約商名稱不能空白\n");
		}else if (form.getSpecial_dealer_name().length() > 25) {
			validateMessage.append("特約商名稱過長\n");
		}
		
		if (StringUtils.isBlank(form.getSpecial_dealer_class())) {
			validateMessage.append("特約商分類不能空白\n");
		}
		
		if (StringUtils.isBlank(form.getShow_address())) {
			validateMessage.append("是否顯示地址不能空白\n");
		}
		
		if (StringUtils.isBlank(form.getShow_phone())) {
			validateMessage.append("是否顯示電話不能空白\n");
		}
	
		if (StringUtils.isBlank(form.getImage())) {
			validateMessage.append("圖片不能空白\n");
		}
		
		if (StringUtils.isBlank(form.getImage_app())) {
			validateMessage.append("行動裝置圖片不能空白\n");			
		}
				
		if (StringUtils.isBlank(form.getBasic_redemption_pc())) {
			validateMessage.append("基本兌點方式(PC)不能空白\n");	
		}else if (form.getBasic_redemption_pc().length() > 150) {
			validateMessage.append("基本兌點方式(APP)內容過長\n");	
		}
		
		if (StringUtils.isBlank(form.getBasic_redemption_app())) {
			validateMessage.append("基本兌點方式(APP)不能空白\n");	
		}else if (form.getBasic_redemption_app().length() > 150) {
			validateMessage.append("基本兌點方式(APP)內容過長\n");	
		}
		
		if (StringUtils.isBlank(form.getIs_show())) {
			validateMessage.append("是否呈現不能空白\n");				
		}
		
		if (StringUtils.isBlank(form.getMobile_phone_barcode())) {
			validateMessage.append("手機條碼哪裡用不能空白\n");				
		}		
		
		if (form.getContract_start_time() == null) {
			validateMessage.append("合約起日期不能空白\n");				
		}
		
		if (form.getContract_end_time() == null) {
			validateMessage.append("合約訖日期不能空白\n");				
		}
		
		if (form.getContract_start_time() != null && form.getContract_end_time() != null) {
			if (form.getContract_end_time().before(form.getContract_start_time())) {
				validateMessage.append("結束時間不可早於起始時間\n");				
			}
		}
		
		if(!ValidatorUtil.isValidUrl(form.getLink()) && !StringUtils.isBlank(form.getLink())) {
			validateMessage.append("請輸入合法網址\n");
		}
		
		if (StringUtils.isBlank(form.getOpen_method())){
			validateMessage.append("連結開啟方式\n");
		}
		
		if (StringUtils.isBlank(form.getStatus())) {
			validateMessage.append("狀態不能空白\n");
		}
	}
	
	

	/**
	 * 檔案驗證
	 * 
	 * @param errors
	 * @throws Exception
	 */
	public void validate(ExcelForm form, BindingResult result, String uploadFilePath) throws Exception {
				
		if (form.getExcel_file() != null && !form.getExcel_file().isEmpty()) {
			boolean IsContentType = false;
			if (form.getExcel_file().getContentType().indexOf("ms-excel") >= 0 || form.getExcel_file().getContentType().indexOf("openxmlformats-officedocument.spreadsheetml.sheet") >= 0) {
				IsContentType = true;
			}
			if (!IsContentType) {
				result.rejectValue("excel", "error", "上傳的內容必須是excel檔案!");
			} else {
				form.setExcel(FileUtil.getUploadFileName(form.getExcel_file(), uploadFilePath));
				
			}
		} else if (StringUtils.isBlank(form.getExcel())) {
			result.rejectValue("excel", "error", "請上傳Excel");
		}
	}
		
		
		
}