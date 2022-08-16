package com.ddim.happygo.web.admin.login;

/***
 * 建立日期：2015/03/12
 * 程式摘要：com.ddim.happygo.web.admin.login<P> 
 * 類別名稱：LoginForm.java<P>
 * 程式內容說明：登入表單物件<P>
 * @author Yvonne 
 * @version 1.0 
 * @since 1.0
 * */
public class LoginForm {

	private String account;
	private String pwd;
	private String returnURL;
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
	 * @return the returnURL
	 */
	public String getReturnURL() {
		return returnURL;
	}
	/**
	 * @param returnURL the returnURL to set
	 */
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

}
