package com.ddim.happygo.web.admin.marquee;

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

public class MarqueeForm {

	private String id;
	private String name;
	private Timestamp start_time;
	private Timestamp end_time;
	private String status;
	private String link;
	private String open_method;
	private int sort;
	private String[] ids;
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
	 * @return the start_time
	 */
	public Timestamp getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	/**
	 * @return the end_time
	 */
	public Timestamp getEnd_time() {
		return end_time;
	}
	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
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
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the open_method
	 */
	public String getOpen_method() {
		return open_method;
	}
	/**
	 * @param open_method the open_method to set
	 */
	public void setOpen_method(String open_method) {
		this.open_method = open_method;
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
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param ids the ids to set
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
		
}