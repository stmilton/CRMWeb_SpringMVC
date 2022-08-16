package com.ddim.happygo.model;

import java.io.Serializable;
import java.sql.Timestamp;

/***
 * 建立日期：2016/1/10 
 * 程式摘要：com.ddim.mpos.domain
 * 類別名稱：CardMemberJoinBasic.java
 * 程式內容說明：後台功能所屬角色物件
 * @author Yvonne
 * */
public class ManagerRoleTask implements Serializable {

	private static final long serialVersionUID = 1L;

	private String role_id;
	private String task_id;
	private String creator;
	private Timestamp create_time;
	/**
	 * @return the role_id
	 */
	public String getRole_id() {
		return role_id;
	}
	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	/**
	 * @return the task_id
	 */
	public String getTask_id() {
		return task_id;
	}
	/**
	 * @param task_id the task_id to set
	 */
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the create_time
	 */
	public Timestamp getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}