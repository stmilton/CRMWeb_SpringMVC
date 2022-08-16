/*
 Created on : 2012/8/20
 */
package com.ddim.happygo.web.admin.tinymce;

import org.springframework.web.multipart.MultipartFile;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.tinymce<P> 
 * 類別名稱：RoleForm.java<P>
 * 程式內容說明：編輯器上傳表單表單物件<P>
 * @author Ringo 
 * @version 1.0 
 * @since 1.0
 * */
public class UploadImgForm {

	private MultipartFile file;
	private String alt;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}
}
