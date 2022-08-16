package com.ddim.happygo.web.admin.footer;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.footer<P> 
 * 類別名稱：FooterForm.java<P>
 * 程式內容說明：最新消息表單物件<P>
 * @author Yvonne 
 * @version 1.0 
 * @since 1.0
 * */

public class FooterForm {

	private String id;
	private String name;
	private String link;
	private String open_method;
	private int sort;
	private String status;
	private String[] ids;
	private MultipartFile img_file;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOpen_method() {
		return open_method;
	}

	public void setOpen_method(String open_method) {
		this.open_method = open_method;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public MultipartFile getImg_file() {
		return img_file;
	}

	public void setImg_file(MultipartFile img_file) {
		this.img_file = img_file;
	}

}