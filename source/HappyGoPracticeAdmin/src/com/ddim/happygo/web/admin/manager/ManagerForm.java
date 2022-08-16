package com.ddim.happygo.web.admin.manager;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.manager<P> 
 * 類別名稱：ManagerForm.java<P>
 * 程式內容說明：後台管理者表單物件<P>
 * @author Yvonne 
 * @version 1.0 
 * @since 1.0
 * */
public class ManagerForm {

	private String id;
	private String account;
	private String pwd;
	private String name;
	private String email;
	private String tel;
	private String mobile;
	private String role_id;
	private String status;
	private String newPwd;
	private String[] ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}