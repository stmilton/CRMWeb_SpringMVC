package com.ddim.happygo.model;

import java.io.Serializable;
import java.util.List;

/***
 * 建立日期：2016/1/10 
 * 程式摘要：com.ddim.mpos.domain
 * 類別名稱：CardMemberJoinBasic.java
 * 程式內容說明：後台功能項目物件
 * @author Yvonne
 * */
public class ManagerTask implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String url;
	private String parent_id;
	private int sort;
	private String status;
	private List<ManagerTask> childs;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the parent_id
	 */
	public String getParent_id() {
		return parent_id;
	}
	/**
	 * @param parent_id the parent_id to set
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the childs
	 */
	public List<ManagerTask> getChilds() {
		return childs;
	}
	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<ManagerTask> childs) {
		this.childs = childs;
	}

}
