package com.ddim.happygo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ddim.happygo.dao.SpecialDealerDao;
import com.ddim.happygo.model.SpecialDealer;
import com.ddim.happygo.util.ExcelUtils;
import com.ddim.happygo.util.NewExcelUtils;
import com.ddim.happygo.web.admin.specialDealer.SpecialDealerForm;
import com.mdbs.jdbc.Page;
import com.mdbs.jdbc.PagingParameter;
import com.mdbs.util.Id;

/**
 * 建立日期：2015年3月9日 程式摘要：com.ddim.happygo.service
 * <P>
 * 類別名稱：ManagerTaskService.java
 * <P>
 * 程式內容說明：最新消息服務
 * <P>
 * 
 * @author Yvonne
 */
@Service
public class ExcelToSpecialDealerService {
	
	@Autowired
	private SpecialDealerDao specialDealerDao;

	/**
	 * excel轉Entity
	 * 
	 * @param entity
	 * @param modifier
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<SpecialDealerForm> excelToForm(MultipartFile file) throws Exception {
		return ExcelUtils.readMultipartFile(file, SpecialDealerForm.class);
	}
	
	/**
	 * 取得所有特約商Map
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getSpecialDealersMap() throws Exception {	
		return specialDealerDao.getSpecialDealers();	
	}
	
	/**
	 * 取得所有特約商表格
	 * @return
	 * @throws Exception
	 */
	public List<List<Object>> exportExcel() throws Exception   {
		
		List<List<Object>> sheetDataList = new ArrayList<>();
		
		// 表頭資料
		List<Object> head = new ArrayList<Object>();
		head.add("總公司名稱");
		head.add("特約商名稱");
		head.add("特約商代碼");
		head.add("特約商分類");
		head.add("郵遞區號");
		head.add("地址");
		head.add("是否顯示地址");
		head.add("電話");
		head.add("是否顯示電話");
		head.add("簡介(PC)");
		head.add("簡介(APP)");
		head.add("圖片");		
		head.add("行動裝置圖片");
		head.add("TOP27用圖片");
		head.add("基本兌點方式(PC)");
		head.add("基本兌點方式(APP)");
		head.add("是否呈現");
		head.add("手機條碼哪裡用");		
		head.add("合約起日期");
		head.add("合約訖日期");
		head.add("連結網址");		
		head.add("是否顯示連結網址");
		head.add("連結開啟方式");
		head.add("狀態");
		
		sheetDataList.add(head);
		
		// 使用者資料
		List<Map<String, Object>> specialDealers = getSpecialDealersMap();
		
		for(Map<String, Object> dealerMap : specialDealers) {
			List<Object> tempSpecialDealer = new ArrayList<>();
			tempSpecialDealer.add(dealerMap.get("head_office_name"));
			tempSpecialDealer.add(dealerMap.get("special_dealer_name"));
			tempSpecialDealer.add(dealerMap.get("special_dealer_code"));
			tempSpecialDealer.add(dealerMap.get("special_dealer_class"));
			tempSpecialDealer.add(dealerMap.get("zipcode"));
			tempSpecialDealer.add(dealerMap.get("address"));
			tempSpecialDealer.add(dealerMap.get("show_address"));
			tempSpecialDealer.add(dealerMap.get("phone"));
			tempSpecialDealer.add(dealerMap.get("show_phone"));
			tempSpecialDealer.add(dealerMap.get("introduce_pc"));
			tempSpecialDealer.add(dealerMap.get("introduce_app"));
			tempSpecialDealer.add(dealerMap.get("image"));
			tempSpecialDealer.add(dealerMap.get("image_app"));
			tempSpecialDealer.add(dealerMap.get("top27_image"));
			tempSpecialDealer.add(dealerMap.get("basic_redemption_pc"));
			tempSpecialDealer.add(dealerMap.get("basic_redemption_app"));
			tempSpecialDealer.add(dealerMap.get("is_show"));
			tempSpecialDealer.add(dealerMap.get("mobile_phone_barcode"));
			tempSpecialDealer.add(dealerMap.get("contract_start_time"));
			tempSpecialDealer.add(dealerMap.get("contract_end_time"));
			tempSpecialDealer.add(dealerMap.get("link"));
			tempSpecialDealer.add(dealerMap.get("show_link"));
			tempSpecialDealer.add(dealerMap.get("open_method"));
			tempSpecialDealer.add(dealerMap.get("status"));
			
			sheetDataList.add(tempSpecialDealer);
		}		
		return sheetDataList;		
	}
}
