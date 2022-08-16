package com.ddim.happygo.web.admin.majorFunction;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

/***
 * 建立日期：2015/03/12 程式摘要：com.ddim.happygo.web.admin.news
 * <P>
 * 類別名稱：NewsForm.java
 * <P>
 * 程式內容說明：最新消息表單物件
 * <P>
 * 
 * @author Yvonne
 * @version 1.0
 * @since 1.0
 */

public class MajorFunctionForm {

	private String id;
	private String name;
	private String color;
	private String cursor_color;
	private String show_icon;
	private String status;
	private String type;
	private String link;
	private String ad_name;
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the cursor_color
	 */
	public String getCursor_color() {
		return cursor_color;
	}

	/**
	 * @param cursor_color the cursor_color to set
	 */
	public void setCursor_color(String cursor_color) {
		this.cursor_color = cursor_color;
	}

	/**
	 * @return the show_icon
	 */
	public String getShow_icon() {
		return show_icon;
	}

	/**
	 * @param show_icon the show_icon to set
	 */
	public void setShow_icon(String show_icon) {
		this.show_icon = show_icon;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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