package com.ddim.happygo.model;

import java.io.Serializable;
import java.sql.Timestamp;

/***
 * 建立日期：2016/1/10 
 * 程式摘要：com.ddim.mpos.domain
 * 類別名稱：CardMemberJoinBasic.java
 * 程式內容說明：後台管理者物件
 * @author Yvonne
 * */
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String pwd;
	private String name;
	private String email;
	private String tel;
	private String mobile;
	private String role_id;
	private String status;
	private String creator;
	private Timestamp create_time;
	private String modifier;
	private Timestamp modify_time;

	private ManagerRole role;

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
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * @return the modify_time
	 */
	public Timestamp getModify_time() {
		return modify_time;
	}

	/**
	 * @param modify_time the modify_time to set
	 */
	public void setModify_time(Timestamp modify_time) {
		this.modify_time = modify_time;
	}

	/**
	 * @return the role
	 */
	public ManagerRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(ManagerRole role) {
		this.role = role;
	}

}
