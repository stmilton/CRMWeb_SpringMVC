package com.ddim.happygo.web.admin.bigBanner;

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

public class BigBannerForm {

	private String id;
	private String ad_name;
	private Timestamp start_time;
	private Timestamp end_time;
	private String status;
	private String image;
	private String ad_proposal;
	private int sort;
	private String[] ids;
	private MultipartFile img_file;
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
	 * @return the ad_name
	 */
	public String getAd_name() {
		return ad_name;
	}
	/**
	 * @param ad_name the ad_name to set
	 */
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the ad_proposal
	 */
	public String getAd_proposal() {
		return ad_proposal;
	}
	/**
	 * @param ad_proposal the ad_proposal to set
	 */
	public void setAd_proposal(String ad_proposal) {
		this.ad_proposal = ad_proposal;
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
	/**
	 * @return the img_file
	 */
	public MultipartFile getImg_file() {
		return img_file;
	}
	/**
	 * @param img_file the img_file to set
	 */
	public void setImg_file(MultipartFile img_file) {
		this.img_file = img_file;
	}

	
}