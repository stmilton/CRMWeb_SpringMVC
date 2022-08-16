package com.ddim.happygo.web.admin.specialDealer;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.news<P> 
 * 類別名稱：NewsForm.java<P>
 * 程式內容說明：最新消息表單物件<P>
 * @author Yvonne 
 * @version 1.0 
 * @since 1.0
 * */

public class ExcelForm {

	private String id;
	private String excel;	
	private MultipartFile excel_file;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the excel
	 */
	public String getExcel() {
		return excel;
	}

	/**
	 * @param excel the excel to set
	 */
	public void setExcel(String excel) {
		this.excel = excel;
	}

	/**
	 * @return the excel_file
	 */
	public MultipartFile getExcel_file() {
		return excel_file;
	}

	/**
	 * @param excel_file the excel_file to set
	 */
	public void setExcel_file(MultipartFile excel_file) {
		this.excel_file = excel_file;
	}
	
	
	
}